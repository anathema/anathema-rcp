package net.sf.anathema.character.sheet.elements;

import java.awt.Color;

import com.lowagie.text.pdf.PdfContentByte;

public class Box {

  private final Bounds bounds;
  private final PdfContentByte directContent;

  public Box(Bounds bounds, PdfContentByte directContent) {
    this.bounds = bounds;
    this.directContent = directContent;
  }

  public void encode(float lineWidth) {
    directContent.setColorStroke(Color.BLACK);
    directContent.setLineWidth(lineWidth);
    directContent.rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
    directContent.stroke();
  }

  public void encodeTotalType() {
    encode(0.75f);
  }
}