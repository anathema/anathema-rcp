package net.sf.anathema.character.textreport;

import java.util.ArrayList;

import net.sf.anathema.character.report.pdf.AbstractReportHandler;
import net.sf.anathema.character.report.pdf.ICharacterReportWriter;
import net.sf.anathema.character.textreport.encoder.ITextReportEncoder;

public class CharacterTextReportPdfHandler extends AbstractReportHandler {

  @Override
  protected ICharacterReportWriter createReportWriter() {
    return new CharacterTextReportWriter(new TextEncoderExtensionPoint().getEncoders());
  }
}