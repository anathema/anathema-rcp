package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.basics.item.plugin.IBasicItemPluginConstants;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;

public final class SaveEditorJob extends Job {
  private final Display display;
  private final IPersistableEditorInput< ? > editorInput;
  private final Runnable postSave;
  private static final Logger logger = new Logger(IBasicItemPluginConstants.PLUGIN_ID);

  public SaveEditorJob(IPersistableItemEditor editorPart, Display display) {
    this(editorPart.getEditorInput(), new FireDirtyRunnable(editorPart), display);
  }

  public SaveEditorJob(IPersistableEditorInput< ? > editorInput, Runnable postSave, Display display) {
    super(createName(editorInput));
    this.editorInput = editorInput;
    this.postSave = postSave;
    this.display = display;
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
      if (!display.isDisposed()) {
        display.asyncExec(postSave);
      }
      return Status.OK_STATUS;
    }
    catch (SWTException e) {
      throw e;
    }
    catch (Exception e) {
      String message = Messages.StyledTextEditor_SaveErrorMessage;
      return logger.createErrorStatus(NLS.bind(message, editorInput.getName()), e);
    }
    finally {
      monitor.done();
    }
  }
}