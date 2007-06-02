package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.item.BasicItemPlugin;
import net.sf.anathema.basics.item.IPersistableEditorInput;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;

public final class SaveEditorJob extends Job {
  private final Display display;
  private final IPersistableEditorInput< ? > editorInput;
  private final Runnable postSave;

  public SaveEditorJob(IPersistableItemEditor editorPart) {
    this(editorPart.getEditorInput(), new FireDirtyRunnable(editorPart));
  }
  
  private SaveEditorJob(IPersistableEditorInput< ? > editorInput, Runnable postSave) {
    super(createName(editorInput));
    this.editorInput = editorInput;
    this.postSave = postSave;
    this.display = Display.getCurrent();
  }
  
  private static String createName(IEditorInput editorInput) {
    String message = Messages.StyledTextEditor_SaveJobTitle;
    return NLS.bind(message, editorInput.getName());
  }

  @Override
  protected IStatus run(IProgressMonitor monitor) {
    monitor.beginTask(Messages.StyledTextEditor_SaveJobTask, IProgressMonitor.UNKNOWN);
    try {
      editorInput.save(monitor);
      display.asyncExec(postSave);
      return Status.OK_STATUS;
    }
    catch (Exception e) {
      String message = Messages.StyledTextEditor_SaveErrorMessage;
      return BasicItemPlugin.getDefaultInstance().createErrorStatus(NLS.bind(message, editorInput.getName()), e);
    }
    finally {
      monitor.done();
    }
  }
}