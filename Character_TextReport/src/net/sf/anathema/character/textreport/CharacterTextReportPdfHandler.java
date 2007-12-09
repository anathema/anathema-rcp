package net.sf.anathema.character.textreport;

import net.sf.anathema.character.report.pdf.AbstractReportHandler;
import net.sf.anathema.character.report.pdf.ICharacterReportWriter;

public class CharacterTextReportPdfHandler extends AbstractReportHandler {

  @Override
  protected ICharacterReportWriter createReportWriter() {
    return new CharacterTextReportWriter(new TextEncoderExtensionPoint().getEncoders());
  }
}