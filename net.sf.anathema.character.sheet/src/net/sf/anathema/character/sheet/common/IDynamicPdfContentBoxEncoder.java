package net.sf.anathema.character.sheet.common;

import net.sf.anathema.character.core.character.ICharacter;

public interface IDynamicPdfContentBoxEncoder extends IPdfContentBoxEncoder {
  
  public float getHeight(ICharacter character);
}