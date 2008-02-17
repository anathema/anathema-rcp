package net.sf.anathema.campaign.plot.report.util;

import net.sf.anathema.basics.pdfexport.itext.ITextReportUtils;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class SeriesReportUtils {

  private final ITextReportUtils reportUtils = new ITextReportUtils();

  public void printPageNumber(PdfWriter writer, Document document, String pageNumber) {
    PdfContentByte directContent = writer.getDirectContent();
    Rectangle pageSize = document.getPageSize();
    PdfTemplate template = directContent.createTemplate(pageSize.getWidth(), pageSize.getHeight());
    template.moveTo(document.left(), document.bottom() - 11);
    template.lineTo(document.right(), document.bottom() - 11);
    template.stroke();
    directContent.addTemplate(template, 0, 0);
    Phrase pageNumberPhrase = new Phrase(pageNumber, reportUtils.createDefaultFont(8, Font.NORMAL));
    ColumnText.showTextAligned(
        directContent,
        Element.ALIGN_CENTER,
        pageNumberPhrase,
        pageSize.getWidth() / 2,
        document.bottom() - 20,
        0);
  }

  public void textLine(
      PdfContentByte cb,
      float y,
      float left,
      float right,
      String connect,
      Font font,
      String leftString,
      String rightString,
      PdfAction link) {
    Phrase phraseLeft = new Phrase(leftString, font);
    Phrase phraseRight = new Phrase(rightString, font);
    float phraseLeftWidth = ColumnText.getWidth(phraseLeft);
    float phraseRightWidth = ColumnText.getWidth(phraseRight);
    float widthRemaining = right - left - phraseRightWidth - phraseLeftWidth;
    ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, phraseLeft, left, y, 0);
    if (connect != null) {
      float cw = ColumnText.getWidth(new Phrase(connect, font));
      int rep = (int) (widthRemaining / cw);
      if (rep > 0) {
        StringBuffer sb = new StringBuffer();
        for (int k = 0; k < rep; ++k) {
          sb.append(connect);
        }
        ColumnText.showTextAligned(
            cb,
            Element.ALIGN_RIGHT,
            new Phrase(sb.toString(), font),
            right - phraseRightWidth,
            y,
            0);
      }
    }
    ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, phraseRight, right, y, 0);
    if (link != null) {
      cb.setAction(link, left, y, right, y + 12);
    }
  }
}