package net.sf.anathema.framework.item.role;

import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.IItemListener;
import net.sf.anathema.framework.item.IItemRepositoryLocation;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.lib.control.change.IChangeListener;

public class AbstractItemRole<D extends IItemData> implements IItemRole<D> {

  private IItem<D> base;

  @Override
  public void addItemListener(IItemListener listener) {
    this.base.addItemListener(listener);
  }

  @Override
  public D getItemData() {
    return this.base.getItemData();
  }

  @Override
  public String getPrintName() {
    return this.base.getPrintName();
  }

  @Override
  public IItemRepositoryLocation getRepositoryLocation() {
    return this.base.getRepositoryLocation();
  }

  @Override
  public void removeItemListener(IItemListener listener) {
    this.base.removeItemListener(listener);
  }

  @Override
  public void setPrintName(String printName) {
    this.base.setPrintName(printName);
  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    this.base.addDirtyListener(changeListener);
  }

  @Override
  public boolean isDirty() {
    return this.base.isDirty();
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    this.base.removeDirtyListener(changeListener);
  }

  @Override
  public void setClean() {
    this.base.setClean();
  }

  public void setBase(IItem<D> item) {
    this.base = item;
  }

  @Override
  public void addRole(IItemRole<D> role) {
    this.base.addRole(role);
  }

  @Override
  public <R extends IItemRole<D>> R getRole(Class<R> roleClass) {
    return this.base.getRole(roleClass);
  }

  @Override
  public boolean hasRole(Class< ? extends IItemRole<D>> roleClass) {
    return this.base.hasRole(roleClass);
  }
}