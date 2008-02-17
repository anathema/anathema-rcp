package net.sf.anathema.character.textreport.wizard;

import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.basics.pdfexport.message.ExportMessages;
import net.sf.anathema.basics.pdfexport.writer.IReportWriter;
import net.sf.anathema.character.report.wizard.AbstractCharacterExportWizard;
import net.sf.anathema.character.textreport.CharacterTextReportWriter;
import net.sf.anathema.character.textreport.encoder.TextEncoderExtensionPoint;

public class TextReportExportWizard extends AbstractCharacterExportWizard {

  @Override
  protected IReportWriter createCharacterPdfWriter() {
    return new CharacterTextReportWriter(new TextEncoderExtensionPoint().getEncoders());
  }

  @Override
  protected IFileSelectionPageMessages createMessage() {
    return new ExportMessages(Messages.TextExportMessages_PageTitle);
  }
}