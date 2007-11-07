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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
    if (isErrorInput()) {
      return false;
    }
    return getPersistableEditorInput().getItem().isDirty();
  }

  private boolean isErrorInput() {
    return getEditorInput() instanceof ErrorMessageEditorInput;
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
  public void init(final IEditorSite site, IEditorInput input) throws PartInitException {
    try {
      setInput(input);
      setSite(site);
      if (isErrorInput()) {
        return;
      }
      final IPersistableEditorInput<I> itemInput = getPersistableEditorInput();
      I item = itemInput.getItem();
      item.addDirtyListener(dirtyChangeListener);
      setPartName(getEditorInput().getName());
      addDisposable(new DirtyChangeDisposable(itemInput));
      setTitleImage(itemInput.getImageDescriptor().createImage());
      addDisposable(new ImageDisposable(getTitleImage()));
    }
    catch (Exception e) {
      throw new PartInitException("Error initializing styled text editor.", e); //$NON-NLS-1$
    }
  }

  @Override
  public final void createPartControl(Composite parent) {
    IEditorInput input = super.getEditorInput();
    if (input instanceof ErrorMessageEditorInput) {
      new Label(parent, SWT.NONE).setText("Has���. Jawoll");
    }
    else {
      createPartControlForItem(parent);
    }
  }

  protected abstract void createPartControlForItem(Composite parent);

  @Override
  public void dispose() {
    disposables.dispose();
    super.dispose();
  }

  protected final <T extends IDisposable> T addDisposable(T disposable) {
    return disposables.addDisposable(disposable);
  }
}