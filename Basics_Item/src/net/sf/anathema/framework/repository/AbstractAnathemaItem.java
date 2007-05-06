package net.sf.anathema.framework.repository;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.framework.item.IItem;
import net.sf.anathema.framework.item.IItemListener;
import net.sf.anathema.framework.item.IItemRepositoryLocation;
import net.sf.anathema.framework.item.data.IItemData;
import net.sf.anathema.lib.collection.IClosure;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.util.IIdentificate;

public abstract class AbstractAnathemaItem<D extends IItemData> implements IItem<D> {

  private String printName;
  private final RepositoryLocation repositoryLocation;
  private final IIdentificate identificate;
  private final GenericControl<IItemListener> repositoryItemListeners = new GenericControl<IItemListener>();

  public AbstractAnathemaItem() {
    this.repositoryLocation = new RepositoryLocation(this);
    this.identificate = repositoryLocation;
  }

  public AbstractAnathemaItem(IIdentificate identificate) {
    this.repositoryLocation = null;
    this.identificate = identificate;
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

  public final synchronized String getId() {
    return identificate.getId();
  }

  public String getIdProposal() {
    return printName == null ? DEFAULT_PRINT_NAME : printName;
  }

  public String getDisplayName() {
    return printName == null ? DEFAULT_PRINT_NAME : printName;
  }

  public void setPrintName(String printName) {
    if (StringUtilities.isNullOrEmpty(printName)) {
      printName = null;
    }
    if (ObjectUtilities.equals(this.printName, printName)) {
      return;
    }
    this.printName = printName;
    firePrintNameChanged(getDisplayName());
  }

  public IItemRepositoryLocation getRepositoryLocation() {
    return repositoryLocation;
  }

  @Override
  public String toString() {
    return getDisplayName();
  }
}