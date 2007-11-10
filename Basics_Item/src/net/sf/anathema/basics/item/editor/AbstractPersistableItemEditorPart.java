package net.sf.anathema.basics.item.editor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.resource.ImageDisposable;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public abstract class AbstractPersistableItemEditorPart<I extends IItem> extends EditorPart implements
    IPersistableItemEditor {

  private final class DirtyChangeDisposable implements IDisposable {
    private final IPersistableEditorInput<I> itemInput;

    private DirtyChangeDisposable(IPersistableEditorInput<I> itemInput) {
      this.itemInput = itemInput;
    }

    @Override
    public void dispose() {
      itemInput.getItem().removeDirtyListener(dirtyChangeListener);
    }
  }

  private final IChangeListener dirtyChangeListener = new IChangeListener() {
    public void stateChanged() {
      getSite().getShell().getDisplay().asyncExec(new FireDirtyRunnable(AbstractPersistableItemEditorPart.this));
    }
  };

  private final AggregatedDisposable disposables = new AggregatedDisposable();

  private IEditorContent editorContent;

  @Override
  public void doSave(IProgressMonitor monitor) {
    Job saveJob = new SaveEditorJob(this, getSite().getShell().getDisplay());
    saveJob.setRule(ResourcesPlugin.getWorkspace().getRoot());
    saveJob.schedule();
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

  protected abstract IEditorContent createItemEditorContent();

  @Override
  public final void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
    try {
      setInput(input);
      setSite(site);
      if (getEditorInput() instanceof ErrorMessageEditorInput) {
        this.editorContent = new ErrorEditorContent();
        this.editorContent.init(site, input);
        return;
      }
      this.editorContent = createItemEditorContent();
      final IPersistableEditorInput<I> itemInput = getPersistableEditorInput();
      I item = itemInput.getItem();
      item.addDirtyListener(dirtyChangeListener);
      setPartName(getEditorInput().getName());
      addDisposable(new DirtyChangeDisposable(itemInput));
      setTitleImage(itemInput.getImageDescriptor().createImage());
      addDisposable(new ImageDisposable(getTitleImage()));
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