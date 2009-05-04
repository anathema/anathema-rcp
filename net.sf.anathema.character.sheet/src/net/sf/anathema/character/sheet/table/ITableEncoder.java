package net.sf.anathema.character.sheet.table;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface ITableEncoder {

  public float encodeTable(PdfContentByte directContent, ICharacter character, Bounds bounds) throws DocumentException;
}