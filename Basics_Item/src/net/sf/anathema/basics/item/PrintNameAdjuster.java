package net.sf.anathema.basics.item;

import net.sf.anathema.basics.item.data.IItemData;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class PrintNameAdjuster<D extends IItemData> implements IObjectValueChangedListener<String> {

  private final IItem<D> item;

  public PrintNameAdjuster(IItem<D> item) {
    this.item = item;
  }

  public void valueChanged(String newValue) {
    item.setPrintName(newValue);
  }
}