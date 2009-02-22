package net.sf.anathema.character.sheet.content;

import static net.sf.anathema.character.sheet.content.IPdfBoxEncoder.*;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;

import com.lowagie.text.pdf.PdfContentByte;

public class PdfHeaderBoxEncoder {
  private static final int HEADER_FONT_PADDING = 3;
  private static final int HEADER_FONT_SIZE = IVoidStateFormatConstants.HEADER_FONT_SIZE;

  public void encodeHeaderBox(PdfContentByte directContent, Bounds bounds, String title) {
    IPdfEncoder pdfEncoder = new PdfEncoder(directContent);
    pdfEncoder.setFillColorBlack();
    Bounds headerBounds = calculateHeaderBounds(bounds);
    directContent.rectangle(
        headerBounds.x + ARCSPACE,
        headerBounds.y,
        headerBounds.width - 2 * ARCSPACE,
        headerBounds.height);
    directContent.arc(headerBounds.x, headerBounds.y, headerBounds.x + 2 * ARCSPACE, headerBounds.y
        + headerBounds.height, 0, 360);
    directContent.arc(
        headerBounds.getMaxX(),
        headerBounds.y,
        headerBounds.getMaxX() - 2 * ARCSPACE,
        headerBounds.getMaxY(),
        0,
        360);
    directContent.fillStroke();
    setFillColorWhite(directContent);
    directContent.setFontAndSize(IEncodeContext.BASEFONT, HEADER_FONT_SIZE);
    directContent.beginText();
    directContent.showTextAligned(PdfContentByte.ALIGN_CENTER, title, (int) headerBounds.getCenterX(), headerBounds.y
        + HEADER_FONT_PADDING, 0);
    directContent.endText();
  }

  private Bounds calculateHeaderBounds(Bounds bounds) {
    return new Bounds(bounds.x, bounds.y + bounds.height - HEADER_HEIGHT, bounds.width, HEADER_HEIGHT);
  }

  private void setFillColorWhite(PdfContentByte directContent) {
    directContent.setRGBColorFill(255, 255, 255);
  }
}