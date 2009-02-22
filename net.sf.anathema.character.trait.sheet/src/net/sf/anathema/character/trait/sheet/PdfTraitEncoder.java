package net.sf.anathema.character.trait.sheet;

import net.sf.anathema.character.sheet.content.AbstractPdfEncoder;
import net.sf.anathema.character.sheet.elements.Position;

import com.lowagie.text.pdf.PdfContentByte;

public class PdfTraitEncoder extends AbstractPdfEncoder {

  private class Dot implements IShape {
    public void encode(PdfContentByte directContent, Position lowerLeft, int dotIndex, int value) {
      directContent.arc(lowerLeft.x, lowerLeft.y, lowerLeft.x + dotSize, lowerLeft.y + dotSize, 0, 360);
      commitShape(dotIndex < value);
    }
  }

  private interface IShape {
    public void encode(PdfContentByte directContent, Position lowerLeft, int dotIndex, int value);
  }

  private class Square implements IShape {
    public void encode(PdfContentByte directContent, Position lowerLeft, int dotIndex, int value) {
      directContent.rectangle(lowerLeft.x, lowerLeft.y, dotSize, dotSize);
      commitShape(dotIndex < value);
    }
  }

  private static final int SMALL_DOT_SPACING = 2;

  public static PdfTraitEncoder createLargeTraitEncoder(PdfContentByte directContent) {
    return new PdfTraitEncoder(directContent, 14, 10);
  }

  public static PdfTraitEncoder createMediumTraitEncoder(PdfContentByte directContent) {
    return new PdfTraitEncoder(directContent, 12, 8);
  }

  public static PdfTraitEncoder createSmallTraitEncoder(PdfContentByte directContent) {
    return new PdfTraitEncoder(directContent, 11, 6);
  }

  private final int height;
  private final int dotSize;
  private final PdfContentByte directContent;

  private PdfTraitEncoder(PdfContentByte directContent, int height, int dotSize) {
    this.directContent = directContent;
    this.height = height;
    this.dotSize = dotSize;
  }

  private void commitShape(boolean isFilled) {
    if (isFilled) {
      directContent.fillStroke();
    }
    else {
      directContent.stroke();
    }
  }

  public int encodeDotsCenteredAndUngrouped(Position position, float width, int value, int dotCount) {
    return encodeShapeCenteredAndUngrouped(position, width, value, dotCount, new Dot());
  }

  private int encodeGroupedDots(Position position, float width, int value, int dotCount, final int dotSpacing) {
    int groupSpacing = dotCount > 5 ? dotSize / 2 : 0;
    int spacecount = (int) Math.ceil((double) dotCount / 5);
    for (int dot = 0; dot < dotCount; dot++) {
      if (dot % 5 == 0) {
        spacecount--;
      }
      float currentGroupingSpace = spacecount * groupSpacing;
      float rightEdgeX = position.x + width;
      float spaceNeededRight = currentGroupingSpace + (dotCount - dot) * (dotSize + dotSpacing);
      Position lowerLeft = new Position(rightEdgeX - spaceNeededRight, position.y);
      new Dot().encode(directContent, lowerLeft, dot, value);
    }
    return dotCount * dotSize + (dotCount - 1) * dotSpacing + groupSpacing;
  }

  private int encodeShapeCenteredAndUngrouped(Position position, float width, int value, int dotCount, IShape shape) {
    initDirectContent();
    int dotWidth = dotCount * dotSize;
    final float dotSpacing = (width - dotWidth) / (dotCount + 1);
    float neededWidth = dotWidth + (dotCount - 1) * dotSpacing;
    float leftDotX = position.x + (width - neededWidth) / 2;
    for (int dot = 0; dot < dotCount; dot++) {
      shape.encode(directContent, new Position(leftDotX, position.y), dot, value);
      leftDotX += dotSize + dotSpacing;
    }
    return height;
  }

  public int encodeSquaresCenteredAndUngrouped(Position position, float width, int value, int dotCount) {
    return encodeShapeCenteredAndUngrouped(position, width, value, dotCount, new Square());
  }

  public int encodeWithLine(Position position, float width, int value, int dotCount) {
    initDirectContent();
    float dotsWidth = encodeGroupedDots(position, width, value, dotCount, SMALL_DOT_SPACING);
    drawMissingTextLine(directContent, position, width - dotsWidth - 5);
    return height;
  }

  public int encodeWithText(String text, Position position, float width, int value, int dotCount) {
    initDirectContent();
    directContent.beginText();
    directContent.showTextAligned(PdfContentByte.ALIGN_LEFT, text, position.x, position.y, 0);
    directContent.endText();
    encodeGroupedDots(position, width, value, dotCount, SMALL_DOT_SPACING);
    return height;
  }

  public int encodeWithTextAndRectangle(
      String text,
      Position position,
      float width,
      int value,
      boolean favored,
      int dotCount) {
    initDirectContent();
    directContent.rectangle(position.x, position.y, dotSize, dotSize);
    commitShape(favored);
    int squareWidth = dotSize + 2;
    Position usualTraitPosition = new Position(position.x + squareWidth, position.y);
    return encodeWithText(text, usualTraitPosition, width - squareWidth, value, dotCount);
  }

  public int getTraitHeight() {
    return height;
  }

  private void initDirectContent() {
    setDefaultFont(directContent);
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.8f);
  }
}