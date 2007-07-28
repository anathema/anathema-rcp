package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.eclipse.resource.ImageDisposable;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
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
    public void changeOccured() {
      getSite().getShell().getDisplay().asyncExec(new FireDirtyRunnable(AbstractPersistableItemEditorPart.this));
    }
  };

  private final AggregatedDisposable disposables = new AggregatedDisposable();

  @Override
  public void doSave(IProgressMonitor monitor) {
    Job saveJob = new SaveEditorJob(this);
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
  @Override
  public IPersistableEditorInput<I> getEditorInput() {
    return (IPersistableEditorInput<I>) super.getEditorInput();
  }

  @Override
  public boolean isDirty() {
    return getEditorInput().getItem().isDirty();
  }

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  @Override
  public void setPartName(String partName) {
    super.setPartName(partName);
  }

  @Override
  public void init(final IEditorSite site, IEditorInput input) throws PartInitException {
    try {
      setInput(input);
      final IPersistableEditorInput<I> itemInput = getEditorInput();
      I item = itemInput.getItem();
      item.addDirtyListener(dirtyChangeListener);
      addDisposable(new DirtyChangeDisposable(itemInput));
      setSite(site);
      setTitleImage(itemInput.getImageDescriptor().createImage());
      setPartName(getEditorInput().getName());
      addDisposable(new ImageDisposable(getTitleImage()));
    }
    catch (Exception e) {
      throw new PartInitException("Error initializing styled text editor.", e); //$NON-NLS-1$
    }
  }

  @Override
  public void dispose() {
    disposables.dispose();
    super.dispose();
  }

  protected final void addDisposable(IDisposable disposable) {
    disposables.addDisposable(disposable);
  }
}