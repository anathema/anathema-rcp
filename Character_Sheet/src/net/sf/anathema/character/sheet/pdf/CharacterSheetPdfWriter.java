package net.sf.anathema.character.sheet.pdf;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.report.pdf.AbstractReportPdfWriter;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;
import net.sf.anathema.character.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.sheet.page.PdfPageConfiguration;
import net.sf.anathema.character.sheet.page.PdfSecondPageEncoder;

import org.eclipse.core.runtime.IProgressMonitor;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class CharacterSheetPdfWriter extends AbstractReportPdfWriter {

  private final IContentEncoderProvider encoderProvider = new ContentEncoderProvider(
      new RegisteredContentEncoderProvider());
  private final List<IPdfPageEncoder> encoderList = new ArrayList<IPdfPageEncoder>();

  public CharacterSheetPdfWriter() {
    PdfPageConfiguration configuration = PdfPageConfiguration.create(pageSize.getRectangle());
    EncodeContext context = new EncodeContext();
    encoderList.add(new PdfFirstPageEncoder(encoderProvider, configuration, context));
    encoderList.add(new PdfSecondPageEncoder(encoderProvider, configuration, context));
  }

  @Override
  protected void performPrint(IProgressMonitor monitor, ICharacter character, Document document, PdfWriter writer)
      throws DocumentException {
    PdfContentByte directContent = writer.getDirectContent();
    monitor.subTask(Messages.CharacterSheetPdfWriter_SubTaskSheet);
    boolean isFirstPrinted = false;
    for (IPdfPageEncoder encoder : encoderList) {
      if (isFirstPrinted) {
        document.newPage();
      }
      else {
        isFirstPrinted = true;
      }
      encoder.encode(document, directContent, character);
      monitor.worked(1);
    }
  }

  @Override
  public int getTaskCount() {
    return encoderList.size();
  }
}