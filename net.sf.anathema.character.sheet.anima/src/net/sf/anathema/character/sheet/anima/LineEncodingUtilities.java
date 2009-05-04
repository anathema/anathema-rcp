package net.sf.anathema.character.sheet.anima;

import net.sf.anathema.character.sheet.elements.Line;
import net.sf.anathema.character.sheet.elements.Position;

import com.lowagie.text.pdf.PdfContentByte;

public class LineEncodingUtilities {

  public static void encodeHorizontalLines(
      PdfContentByte directContent,
      Position lineStartPosition,
      float minX,
      float maxX,
      float lineHeight,
      int lineCount) {
    if (lineCount <= 0) {
      return;
    }
    Line.createHorizontalByCoordinate(lineStartPosition, maxX).encode(directContent);
    for (int index = 1; index < lineCount; index++) {
      lineStartPosition = new Position(minX, lineStartPosition.y - lineHeight);
      Line.createHorizontalByCoordinate(lineStartPosition, maxX).encode(directContent);
    }
  }
}