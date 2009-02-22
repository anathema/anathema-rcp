package net.sf.anathema.character.spiritualtraits.sheet;

import net.sf.anathema.character.sheet.content.AbstractPdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;

import com.lowagie.text.pdf.PdfContentByte;

public class EssencePoolEncoder extends AbstractPdfEncoder {

  private final PdfContentByte directContent;

  public EssencePoolEncoder(PdfContentByte directContent) {
    this.directContent = directContent;
  }

  public void encode(Bounds bounds) {
    float poolLineHeight = (bounds.height - IVoidStateFormatConstants.TEXT_PADDING) / 2;
    Position personalPosition = new Position(bounds.x, bounds.getMaxY() - poolLineHeight);
    encodePool("Personal Pool", "0", personalPosition, bounds.width);
    Position peripheralPosition = new Position(bounds.x, bounds.getMaxY() - 2 * poolLineHeight);
    encodePool("Peripheral Pool", "0", peripheralPosition, bounds.width);
  }

  private void encodePool(String label, String poolValue, Position poolPosition, float width) {
    drawText(directContent, label, poolPosition, PdfContentByte.ALIGN_LEFT);
    String availableString = " Available";
    float availableTextWidth = getDefaultTextWidth(availableString);
    Position availablePosition = new Position(poolPosition.x + width, poolPosition.y);
    drawText(directContent, availableString, availablePosition, PdfContentByte.ALIGN_RIGHT);
    float lineLength = 10;
    Position lineStartPoint = new Position(
        (int) (availablePosition.x - availableTextWidth - lineLength),
        poolPosition.y);
    drawMissingTextLine(directContent, lineStartPoint, lineLength);
    String totalString = poolValue + " Total / ";
    drawText(directContent, totalString, lineStartPoint, PdfContentByte.ALIGN_RIGHT);
  }
}