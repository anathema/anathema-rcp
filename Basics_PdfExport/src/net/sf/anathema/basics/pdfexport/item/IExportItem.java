package net.sf.anathema.basics.pdfexport.item;

public interface IExportItem<T> {
  
  public T createItem();

  public String getPrintName();
}