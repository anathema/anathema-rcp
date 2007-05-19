package net.sf.anathema.framework.repository;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.IItemListener;
import net.sf.anathema.framework.item.IItemRepositoryLocation;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.framework.item.role.IItemRole;
import net.sf.anathema.lib.collection.IClosure;
import net.sf.anathema.lib.control.GenericControl;

public abstract class AbstractAnathemaItem<D extends IItemData> implements IItem<D> {

  private String printName;
  private final RepositoryLocation repositoryLocation;
  private final GenericControl<IItemListener> repositoryItemListeners = new GenericControl<IItemListener>();
  private final List<IItemRole<D>> roles = new ArrayList<IItemRole<D>>();

  public AbstractAnathemaItem() {
    this.repositoryLocation = new RepositoryLocation(this);
  }

  public void addItemListener(IItemListener listener) {
    repositoryItemListeners.addListener(listener);
  }

  public void removeItemListener(IItemListener listener) {
    repositoryItemListeners.removeListener(listener);
  }

  private void firePrintNameChanged(final String name) {
    repositoryItemListeners.forAllDo(new IClosure<IItemListener>() {
      public void execute(IItemListener input) {
        input.printNameChanged(name);
      }
    });
  }

  public String getIdProposal() {
    return printName == null ? DEFAULT_PRINT_NAME : printName;
  }

  public String getPrintName() {
    return printName == null ? DEFAULT_PRINT_NAME : printName;
  }

  public void setPrintName(String printName) {
    String newPrintName = StringUtilities.isNullOrEmpty(printName) ? null : printName;
    if (ObjectUtilities.equals(this.printName, newPrintName)) {
      return;
    }
    this.printName = newPrintName;
    firePrintNameChanged(getPrintName());
  }

  public IItemRepositoryLocation getRepositoryLocation() {
    return repositoryLocation;
  }

  @Override
  public String toString() {
    return getPrintName();
  }

  @Override
  public void addRole(IItemRole<D> role) {
    roles.add(role);
  }

  @Override
  public boolean hasRole(Class< ? extends IItemRole<D>> roleClass) {
    return getRole(roleClass) != null;
  }

  @Override
  public <R extends IItemRole<D>> R getRole(Class<R> roleClass) {
    for (IItemRole<D> role : roles) {
      if (roleClass.isInstance(role)) {
        return roleClass.cast(role);
      }
    }
    return null;
  }
}