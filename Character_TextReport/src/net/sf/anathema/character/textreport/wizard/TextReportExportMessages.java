package net.sf.anathema.character.textreport.wizard;

import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;

public final class TextReportExportMessages implements IFileSelectionPageMessages {
  @Override
  public String getPageTitle() {
    return Messages.SheetExportMessages_PageTitle;
  }

  @Override
  public String getPageName() {
    return Messages.SheetExportMessages_PageTitle;
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