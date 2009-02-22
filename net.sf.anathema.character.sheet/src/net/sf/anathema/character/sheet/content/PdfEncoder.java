package net.sf.anathema.character.sheet.content;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public final class PdfEncoder extends UnconfiguredExecutableExtension implements IGraphicalEncoder {

  private final PdfContentByte directContent;

  public PdfEncoder(PdfContentByte directContent) {
    this.directContent = directContent;
  }

  protected final BaseFont getBaseFont() {
    return IEncodeContext.BASEFONT;
  }

  public final void setCommentFont() {
    directContent.setFontAndSize(getBaseFont(), IVoidStateFormatConstants.COMMENT_FONT_SIZE);
  }

  public final void setDefaultFont() {
    directContent.setFontAndSize(getBaseFont(), IVoidStateFormatConstants.FONT_SIZE);
  }

  public final int getDefaultTextWidth(String text) {
    return (int) getBaseFont().getWidthPoint(text, IVoidStateFormatConstants.FONT_SIZE);
  }

  public final int getCommentTextWidth(String text) {
    return (int) getBaseFont().getWidthPoint(text, IVoidStateFormatConstants.COMMENT_FONT_SIZE);
  }

  public final void setFillColorBlack() {
    directContent.setRGBColorFill(0, 0, 0);
  }

  public final void drawMissingTextLine(Position position, float length) {
    setFillColorBlack();
    directContent.setLineWidth(0);
    directContent.moveTo(position.x, position.y);
    directContent.lineTo(position.x + length, position.y);
    directContent.stroke();
  }

  public final void drawComment(String text, Position position, int alignment) {
    setFillColorBlack();
    setCommentFont();
    directContent.setLineWidth(0);
    drawText(text, position, alignment, 0);
  }

  public final void drawText(String text, Position position, int alignment) {
    addText(text, position, alignment, 0);
  }

  public final void drawVerticalText(String text, Position position, int alignment) {
    addText(text, position, alignment, 90);
  }

  private void addText(String text, Position position, int alignment, int rotation) {
    initDirectContentForText();
    drawText(text, position, alignment, rotation);
  }

  private void drawText(String text, Position position, int alignment, int rotation) {
    directContent.beginText();
    directContent.showTextAlignedKerned(alignment, text, position.x, position.y, rotation);
    directContent.endText();
  }

  public final void drawLabelledContent(String label, String content, Position position, float width) {
    initDirectContentForText();
    directContent.beginText();
    directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, label, position.x, position.y, 0);
    float labelWidth = getDefaultTextWidth(label);
    float contentX = position.x + labelWidth + 2;
    if (StringUtilities.isNullOrTrimmedEmpty(content)) {
      directContent.endText();
      float lineWidth = position.x + width - contentX;
      drawMissingTextLine(new Position(contentX, position.y), lineWidth);
    }
    else {
      directContent.showTextAlignedKerned(PdfContentByte.ALIGN_LEFT, content, contentX, position.y, 0);
      directContent.endText();
    }
  }

  public void drawRectangle(Position position, int size, boolean filled) {
    initDirectContentForShapes();
    directContent.rectangle(position.x, position.y, size, size);
    commitShape(filled);
  }

  public void drawCircle(Position lowerLeft, int size, boolean isFilled) {
    initDirectContentForShapes();
    directContent.arc(lowerLeft.x, lowerLeft.y, lowerLeft.x + size, lowerLeft.y + size, 0, 360);
    commitShape(isFilled);
  }

  private void commitShape(boolean isFilled) {
    if (isFilled) {
      directContent.fillStroke();
    }
    else {
      directContent.stroke();
    }
  }

  private void initDirectContentForShapes() {
    setDefaultFont();
    setFillColorBlack();
    directContent.setLineWidth(0.8f);
  }

  private void initDirectContentForText() {
    setFillColorBlack();
    setDefaultFont();
    directContent.setLineWidth(0);
  }
}