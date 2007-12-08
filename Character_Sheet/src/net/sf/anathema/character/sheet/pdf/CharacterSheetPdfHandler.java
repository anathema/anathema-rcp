package net.sf.anathema.character.sheet.pdf;

import net.sf.anathema.character.report.pdf.AbstractReportHandler;
import net.sf.anathema.character.report.pdf.ICharacterReportWriter;

public class CharacterSheetPdfHandler extends AbstractReportHandler {

  @Override
  protected ICharacterReportWriter createReportWriter() {
    return new CharacterSheetPdfWriter();
  }
}