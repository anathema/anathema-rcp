package net.sf.anathema.basics.pdfexport.writer;

public interface IExportItem<T> {
  
  public T createItem();

  public String getPrintName();
}