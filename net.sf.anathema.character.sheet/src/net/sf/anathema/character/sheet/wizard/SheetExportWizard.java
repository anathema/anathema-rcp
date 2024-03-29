package net.sf.anathema.character.sheet.wizard;

import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.pdfexport.message.ExportMessages;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.report.wizard.AbstractCharacterExportWizard;
import net.sf.anathema.character.sheet.pdf.CharacterSheetPdfWriter;

public class SheetExportWizard extends AbstractCharacterExportWizard {

  @Override
  protected IReportWriter<ICharacter> createCharacterPdfWriter() {
    return new CharacterSheetPdfWriter();
  }

  @Override
  protected IFileSelectionPageMessages createMessage() {
    return new ExportMessages(Messages.SheetExportMessages_PageTitle);
  }
}