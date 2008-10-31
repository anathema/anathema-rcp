package net.sf.anathema.character.abilities.sheet;

import net.sf.anathema.character.sheet.elements.Position;

import com.lowagie.text.pdf.PdfContentByte;

public class MarkerEncoder {

  public final void encode(PdfContentByte directContent, Position markerPosition) {
    directContent.setLineWidth(1.0f);
    directContent.moveTo(markerPosition.x, markerPosition.y + 2);
    directContent.lineTo(markerPosition.x + 4, markerPosition.y + 2);
    directContent.moveTo(markerPosition.x + 2, markerPosition.y);
    directContent.lineTo(markerPosition.x + 2, markerPosition.y + 4);
    directContent.stroke();
  }
}