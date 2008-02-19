package net.sf.anathema.basics.pdfexport.item;

public class ExportItem implements IExportItem {

  private final String printName;

  public ExportItem(String printName) {
    this.printName = printName;
  }
  
  @Override
  public String getPrintName() {
    return printName;
  }
}