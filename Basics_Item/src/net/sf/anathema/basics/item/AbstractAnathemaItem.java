package net.sf.anathema.basics.item;

import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.item.data.IItemData;
import net.sf.anathema.lib.collection.IClosure;
import net.sf.anathema.lib.control.GenericControl;

public abstract class AbstractAnathemaItem<D extends IItemData> implements IItem<D> {

  private String printName;

  private final GenericControl<IItemListener> repositoryItemListeners = new GenericControl<IItemListener>();

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

  @Override
  public String toString() {
    return getPrintName();
  }
}