package net.sf.anathema.character.textreport.wizard;

import net.sf.anathema.basics.importexport.IFileSelectionPageMessages;
import net.sf.anathema.character.report.wizard.AbstractCharacterExportWizard;
import net.sf.anathema.character.report.wizard.ICharacterReportWriter;
import net.sf.anathema.character.textreport.CharacterTextReportWriter;
import net.sf.anathema.character.textreport.encoder.TextEncoderExtensionPoint;

public class TextReportExportWizard extends AbstractCharacterExportWizard {

  @Override
  protected ICharacterReportWriter createCharacterPdfWriter() {
    return new CharacterTextReportWriter(new TextEncoderExtensionPoint().getEncoders());
  }

  @Override
  protected IFileSelectionPageMessages createMessage() {
    return new TextReportExportMessages();
  }
}