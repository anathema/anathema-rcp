package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;
import net.sf.anathema.lib.control.change.IChangeListener;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public abstract class AbstractPersistableItemEditorPart<I extends IItem> extends EditorPart implements IPersistableItemEditor {

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
      IPersistableEditorInput<I> itemInput = getEditorInput();
      I item = itemInput.getItem();
      item.addDirtyListener(new IChangeListener() {
        public void changeOccured() {
          getSite().getShell().getDisplay().asyncExec(new FireDirtyRunnable(AbstractPersistableItemEditorPart.this));
        }
      });
      setSite(site);
      setTitleImage(itemInput.getImageDescriptor().createImage());
      setPartName(getEditorInput().getName());
    }
    catch (Exception e) {
      throw new PartInitException("Error initializing styled text editor.", e); //$NON-NLS-1$
    }
  }
}