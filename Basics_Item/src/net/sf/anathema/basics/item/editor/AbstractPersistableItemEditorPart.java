package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.IPersistableEditorInput;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.part.EditorPart;

public abstract class AbstractPersistableItemEditorPart extends EditorPart implements IPersistableItemEditor {

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

  @Override
  public IPersistableEditorInput<?> getEditorInput() {
    return (IPersistableEditorInput< ? >) super.getEditorInput();
  }

  protected abstract IItem getItem();

  @Override
  public boolean isDirty() {
    return getItem().isDirty();
  }

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  @Override
  public void setPartName(String partName) {
    super.setPartName(partName);
  }
}