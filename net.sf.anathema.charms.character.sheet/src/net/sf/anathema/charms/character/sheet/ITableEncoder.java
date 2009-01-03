package net.sf.anathema.charms.character.sheet;

import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface ITableEncoder {

  float encodeTable(PdfContentByte directContent, Bounds bounds) throws DocumentException;
}