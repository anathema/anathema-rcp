package net.sf.anathema.character.spiritualtraits.sheet;

import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;

import com.lowagie.text.pdf.PdfContentByte;

public class EssencePoolEncoder {

  private final PdfEncoder pdfEncoder;

  public EssencePoolEncoder(PdfContentByte directContent) {
    this.pdfEncoder = new PdfEncoder(directContent);
  }

  public void encode(Bounds bounds) {
    float poolLineHeight = (bounds.height - IVoidStateFormatConstants.TEXT_PADDING) / 2;
    Position personalPosition = new Position(bounds.x, bounds.getMaxY() - poolLineHeight);
    encodePool("Personal Pool", "0", personalPosition, bounds.width);
    Position peripheralPosition = new Position(bounds.x, bounds.getMaxY() - 2 * poolLineHeight);
    encodePool("Peripheral Pool", "0", peripheralPosition, bounds.width);
  }

  private void encodePool(String label, String poolValue, Position poolPosition, float width) {
    pdfEncoder.drawText(label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = " Available";
    float availableTextWidth = pdfEncoder.getDefaultTextWidth(availableString);
    Position availablePosition = new Position(poolPosition.x + width, poolPosition.y);
    pdfEncoder.drawText(availableString, availablePosition, PdfContentByte.ALIGN_RIGHT);
    float lineLength = 10;
    Position lineStartPoint = new Position(
        (int) (availablePosition.x - availableTextWidth - lineLength),
        poolPosition.y);
    pdfEncoder.drawMissingTextLine(lineStartPoint, lineLength);
    String totalString = poolValue + " Total / ";
    pdfEncoder.drawText(totalString, lineStartPoint, PdfContentByte.ALIGN_RIGHT);
  }
}