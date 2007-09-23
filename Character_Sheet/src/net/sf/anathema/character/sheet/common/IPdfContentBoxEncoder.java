package net.sf.anathema.character.sheet.common;

import net.sf.anathema.character.sheet.content.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface IPdfContentBoxEncoder {

  public void encode(PdfContentByte directContent, ICharacter character, Bounds bounds) throws DocumentException;

  public String getHeader();
}