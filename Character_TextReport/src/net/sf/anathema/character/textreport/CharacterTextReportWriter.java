package net.sf.anathema.character.textreport;

import java.io.OutputStream;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.report.pdf.ICharacterReportWriter;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.DocumentException;

public class CharacterTextReportWriter implements ICharacterReportWriter {

  @Override
  public int getTaskCount() {
    return 0;
  }

  @Override
  public void write(IProgressMonitor monitor, ICharacter character, OutputStream outputStream) throws DocumentException {
    System.err.println("Tadaaa");
  }
}