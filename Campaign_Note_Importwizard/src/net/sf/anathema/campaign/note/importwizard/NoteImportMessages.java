package net.sf.anathema.campaign.note.importwizard;

import net.sf.anathema.basics.importwizard.IFileSelectionPageMessages;

public class NoteImportMessages implements IFileSelectionPageMessages {

  @Override
  public String getPageName() {
    return Messages.NoteFileSelectionWizardPage_PageName;
  }

  @Override
  public String getPageTitle() {
    return Messages.NoteFileSelectionWizardPage_PageName;
  }

  @Override
  public String getDescription() {
    return Messages.NoteFileSelectionWizardPage_Description;
  }

  @Override
  public String getOpenLabel() {
    return Messages.NoteFileSelectionWizardPage_OpenNoteLabel;
  }
}