package net.sf.anathema.character.sheet.print;

import java.awt.Color;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.sheet.ICharacter;
import net.sf.anathema.character.sheet.page.IPdfPageEncoder;
import net.sf.anathema.character.sheet.page.PdfFirstPageEncoder;
import net.sf.anathema.character.sheet.page.PdfSecondPageEncoder;
import net.sf.anathema.character.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.sheet.util.IContentEncoderProvider;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class Printer {
  private final PageSize pageSize = PageSize.A4;
  private final BaseFont baseFont = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);

  public void printReport(OutputStream outputStream) throws DocumentException {
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, outputStream);
    writer.setPdfVersion(PdfWriter.VERSION_1_5);
    writer.setViewerPreferences(PdfWriter.DisplayDocTitle);
    document.addTitle("Unknown Title");
    document.addCreator("Anathema"); //$NON-NLS-1$
    document.open();
    performPrint(document, writer);
    document.close();
  }

  private void performPrint(Document document, PdfWriter writer) throws DocumentException {
    ICharacter character = null;
    document.setPageSize(pageSize.getRectangle());
    document.open();
    PdfContentByte directContent = writer.getDirectContent();
    PdfPageConfiguration configuration = PdfPageConfiguration.create(pageSize.getRectangle());
    IContentEncoderProvider encoderProvider = new NullContentEncoderProvider();
    List<IPdfPageEncoder> encoderList = new ArrayList<IPdfPageEncoder>();
    encoderList.add(new PdfFirstPageEncoder(encoderProvider, configuration, baseFont));
    encoderList.add(new PdfSecondPageEncoder(encoderProvider, configuration, baseFont));
    boolean isFirstPrinted = false;
    for (IPdfPageEncoder encoder : encoderList) {
      if (isFirstPrinted) {
        document.newPage();
      }
      else {
        isFirstPrinted = true;
      }
      encoder.encode(document, directContent, character);
    }
  }
}