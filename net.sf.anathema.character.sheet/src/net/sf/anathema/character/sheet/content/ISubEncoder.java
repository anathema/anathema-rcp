package net.sf.anathema.character.sheet.content;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public interface ISubEncoder {

  public float encode(PdfContentByte directContent, ICharacter character, Bounds bounds) throws DocumentException;
}