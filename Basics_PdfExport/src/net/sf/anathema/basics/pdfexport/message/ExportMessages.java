package net.sf.anathema.basics.pdfexport.message;

import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;

public class ExportMessages implements IFileSelectionPageMessages {

  private final String name;

  public ExportMessages(String name) {
    this.name = name;
  }

  @Override
  public String getPageTitle() {
    return name;
  }

  @Override
  public String getPageName() {
    return name;
  }

  @Override
  public String getOpenLabel() {
    return Messages.SheetExportMessages_OpenLabel;
  }

  @Override
  public String getDescription() {
    return Messages.SheetExportMessages_Description;
  }
}