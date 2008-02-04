package net.sf.anathema.character.textreport;

import net.sf.anathema.character.report.pdf.AbstractReportHandler;
import net.sf.anathema.character.report.pdf.ICharacterReportWriter;
import net.sf.anathema.character.textreport.encoder.TextEncoderExtensionPoint;

public class CharacterTextReportPdfHandler extends AbstractReportHandler {

  @Override
  protected ICharacterReportWriter createReportWriter() {
    return new CharacterTextReportWriter(new TextEncoderExtensionPoint().getEncoders());
  }
}