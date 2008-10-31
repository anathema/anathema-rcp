package net.sf.anathema.character.sheet.content;

import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Line;
import net.sf.anathema.character.sheet.elements.Position;

import com.lowagie.text.pdf.PdfContentByte;

public class HorizontalLineListEncoder {

  public void encodeLines(PdfContentByte directContent, Bounds bounds, float lineHeight) {
    float yPosition = bounds.getMaxY() - lineHeight;
    while (yPosition > bounds.getMinY()) {
      Line line = Line.createHorizontalByCoordinate(new Position(bounds.getMinX(), yPosition), bounds.getMaxX());
      line.encode(directContent);
      yPosition -= lineHeight;
    }
  }
}