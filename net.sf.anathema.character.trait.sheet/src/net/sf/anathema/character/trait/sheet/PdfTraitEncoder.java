package net.sf.anathema.character.trait.sheet;

import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.elements.Position;

import com.lowagie.text.pdf.PdfContentByte;

public class PdfTraitEncoder {

  private class Dot implements IShape {
    public void encode(Position lowerLeft, int dotIndex, int value) {
      drawCircle(lowerLeft, dotIndex < value);
    }
  }

  private interface IShape {
    public void encode(Position lowerLeft, int dotIndex, int value);
  }

  private class Square implements IShape {
    public void encode(Position lowerLeft, int dotIndex, int value) {
      drawRectangle(lowerLeft, dotIndex < value);
    }
  }

  private static final int SMALL_DOT_SPACING = 2;

  public static PdfTraitEncoder createLargeTraitEncoder(IGraphicalEncoder pdfEncoder) {
    return new PdfTraitEncoder(pdfEncoder, 14, 10);
  }

  public static PdfTraitEncoder createMediumTraitEncoder(IGraphicalEncoder pdfEncoder) {
    return new PdfTraitEncoder(pdfEncoder, 12, 8);
  }

  public static PdfTraitEncoder createSmallTraitEncoder(IGraphicalEncoder pdfEncoder) {
    return new PdfTraitEncoder(pdfEncoder, 11, 6);
  }

  private final int height;
  private final int dotSize;
  private final IGraphicalEncoder pdfEncoder;

  private PdfTraitEncoder(IGraphicalEncoder pdfEncoder, int height, int dotSize) {
    this.pdfEncoder = pdfEncoder;
    this.height = height;
    this.dotSize = dotSize;
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
      new Dot().encode(lowerLeft, dot, value);
    }
    return dotCount * dotSize + (dotCount - 1) * dotSpacing + groupSpacing;
  }

  private int encodeShapeCenteredAndUngrouped(Position position, float width, int value, int dotCount, IShape shape) {
    int dotWidth = dotCount * dotSize;
    final float dotSpacing = (width - dotWidth) / (dotCount + 1);
    float neededWidth = dotWidth + (dotCount - 1) * dotSpacing;
    float leftDotX = position.x + (width - neededWidth) / 2;
    for (int dot = 0; dot < dotCount; dot++) {
      shape.encode(new Position(leftDotX, position.y), dot, value);
      leftDotX += dotSize + dotSpacing;
    }
    return height;
  }

  public int encodeSquaresCenteredAndUngrouped(Position position, float width, int value, int dotCount) {
    return encodeShapeCenteredAndUngrouped(position, width, value, dotCount, new Square());
  }

  public int encodeWithLine(Position position, float width, int value, int dotCount) {
    float dotsWidth = encodeGroupedDots(position, width, value, dotCount, SMALL_DOT_SPACING);
    pdfEncoder.drawMissingTextLine(position, width - dotsWidth - 5);
    return height;
  }

  public int encodeWithText(String text, Position position, float width, int value, int dotCount) {
    pdfEncoder.drawText(text, position, PdfContentByte.ALIGN_LEFT);
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
    drawRectangle(position, favored);
    int squareWidth = dotSize + 2;
    Position usualTraitPosition = new Position(position.x + squareWidth, position.y);
    return encodeWithText(text, usualTraitPosition, width - squareWidth, value, dotCount);
  }

  private void drawRectangle(Position position, boolean filled) {
    pdfEncoder.drawRectangle(position, dotSize, filled);
  }

  private void drawCircle(Position lowerLeft, boolean isFilled) {
    pdfEncoder.drawCircle(lowerLeft, dotSize, isFilled);
  }

  public int getTraitHeight() {
    return height;
  }
}