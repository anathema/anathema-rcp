package net.sf.anathema.basics.pdfexport.item;

import net.sf.anathema.basics.pdfexport.writer.IExportItem;

public class DummyExportItem<T> implements IExportItem<T> {

  private final String printName;
  private final T item;

  public DummyExportItem(String printName, T item) {
    this.printName = printName;
    this.item = item;
  }

  @Override
  public T createItem() {
    return item;
  }

  @Override
  public String getPrintName() {
    return printName;
  }
}