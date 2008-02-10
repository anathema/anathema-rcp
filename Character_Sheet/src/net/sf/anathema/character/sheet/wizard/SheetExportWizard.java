package net.sf.anathema.character.sheet.wizard;


import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.character.report.wizard.AbstractCharacterExportWizard;
import net.sf.anathema.character.report.wizard.ICharacterReportWriter;
import net.sf.anathema.character.sheet.pdf.CharacterSheetPdfWriter;


public class SheetExportWizard extends AbstractCharacterExportWizard {

  @Override
  protected ICharacterReportWriter createCharacterPdfWriter() {
    return new CharacterSheetPdfWriter();
  }

  @Override
  protected IFileSelectionPageMessages createMessage() {
    return new SheetExportMessages();
  }
}