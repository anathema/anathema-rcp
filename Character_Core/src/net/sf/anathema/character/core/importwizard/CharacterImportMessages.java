package net.sf.anathema.character.core.importwizard;

import net.sf.anathema.basics.importwizard.IImportPageMessages;

public class CharacterImportMessages implements IImportPageMessages {

  @Override
  public String getDescription() {
    return Messages.CharacterImportMessages_Description;
  }

  @Override
  public String getOpenLabel() {
    return Messages.CharacterImportMessages_OpenMessage;
  }

  @Override
  public String getPageName() {
    return Messages.CharacterImportMessages_PageName;
  }

  @Override
  public String getPageTitle() {
    return Messages.CharacterImportMessages_PageName;
  }

}
