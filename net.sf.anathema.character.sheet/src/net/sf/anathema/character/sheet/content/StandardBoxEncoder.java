package net.sf.anathema.character.sheet.content;

import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.pdf.PdfContentByte;

public class StandardBoxEncoder extends AbstractPdfEncoder implements IPdfBoxEncoder {

  public void encodeContentBox(PdfContentByte directContent, Bounds contentBounds) {
    setFillColorBlack(directContent);
    directContent.setLineWidth(0.5f);
    directContent.moveTo(contentBounds.x, contentBounds.y + ARCSPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.y, 180);
    directContent.moveTo(contentBounds.x + ARCSPACE, contentBounds.y);
    directContent.lineTo(contentBounds.x + contentBounds.width - ARCSPACE, contentBounds.y);
    BoxEncodingUtils.add90DegreeArc(
        directContent,
        contentBounds.x + contentBounds.width - ARC_SIZE,
        contentBounds.y,
        270);
    directContent.moveTo(contentBounds.getMaxX(), contentBounds.y + ARCSPACE);
    directContent.lineTo(contentBounds.getMaxX(), contentBounds.getMaxY() - ARCSPACE);
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.getMaxX() - ARC_SIZE, contentBounds.getMaxY()
        - ARC_SIZE, 0);
    directContent.moveTo(contentBounds.getMaxX() - ARCSPACE, contentBounds.getMaxY());
    directContent.lineTo(contentBounds.getMinX() + ARCSPACE, contentBounds.getMaxY());
    BoxEncodingUtils.add90DegreeArc(directContent, contentBounds.x, contentBounds.getMaxY() - ARC_SIZE, 90);
    directContent.moveTo(contentBounds.x, contentBounds.getMaxY() - ARCSPACE);
    directContent.lineTo(contentBounds.x, contentBounds.y + ARCSPACE);
    directContent.stroke();
  }
}