package net.sf.anathema.character.abilities.sheet;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Position;

import com.lowagie.text.pdf.PdfContentByte;

public interface ISubSectionEncoder {

  public int encode(PdfContentByte directContent, ICharacter character, Position position, float width);
}