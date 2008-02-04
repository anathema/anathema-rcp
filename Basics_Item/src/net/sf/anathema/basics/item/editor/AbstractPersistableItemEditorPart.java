package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public abstract class AbstractPersistableItemEditorPart<I extends IItem> extends EditorPart implements
    IPersistableItemEditor {

  private final AggregatedDisposable disposables = new AggregatedDisposable();

  private IEditorControl editorContent;

  @Override
  public void doSave(IProgressMonitor monitor) {
    Runnable postSave = createPostSaveRunnable();
    Job saveJob = new SaveEditorJob(this.getPersistableEditorInput(), postSave, getSite().getShell().getDisplay());
    saveJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
    saveJob.schedule();
  }

  protected Runnable createPostSaveRunnable() {
    return new FireDirtyRunnable(this);
  }

  @Override
  public void doSaveAs() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void firePropertyChange(int propertyId) {
    super.firePropertyChange(propertyId);
  }

  @SuppressWarnings("unchecked")
  public IPersistableEditorInput<I> getPersistableEditorInput() {
    return (IPersistableEditorInput<I>) super.getEditorInput();
  }

  @Override
  public final boolean isDirty() {
    return editorContent.isDirty();
  }

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  @Override
  // protected in superclass
  public void setPartName(String partName) {
    super.setPartName(partName);
  }

  @Override
  // protected in superclass
  public void setTitleImage(Image titleImage) {
    super.setTitleImage(titleImage);
  }

  protected abstract IEditorControl createItemEditorControl();

  @Override
  public final void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
    try {
      setInput(input);
      setSite(site);
      if (getEditorInput() instanceof ErrorMessageEditorInput) {
        this.editorContent = new ErrorEditorContent();
      }
      else {
        this.editorContent = createItemEditorControl();
      }
      if (editorContent instanceof IDisposable) {
        addDisposable((IDisposable) editorContent);
      }
      this.editorContent.init(site, input);
    }
    catch (Exception e) {
      throw new PartInitException("Error initializing styled text editor.", e); //$NON-NLS-1$
    }
  }

  @Override
  public final void createPartControl(Composite parent) {
    editorContent.createPartControl(parent);
  }

  @Override
  public void dispose() {
    disposables.dispose();
    super.dispose();
  }

  protected final <T extends IDisposable> T addDisposable(T disposable) {
    return disposables.addDisposable(disposable);
  }

  @Override
  public final void setFocus() {
    editorContent.setFocus();
  }
}