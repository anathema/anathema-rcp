package net.sf.anathema.character.sheet.wizard;

import net.sf.anathema.basics.importwizard.IFileSelectionPageMessages;

public final class SheetExportMessages implements IFileSelectionPageMessages {
  @Override
  public String getPageTitle() {
    return "Export Character Sheet";
  }

  @Override
  public String getPageName() {
    return "File Selection";
  }

  @Override
  public String getOpenLabel() {
    return "Open file after export";
  }

  @Override
  public String getDescription() {
    return "Select pdf file for export";
  }
}