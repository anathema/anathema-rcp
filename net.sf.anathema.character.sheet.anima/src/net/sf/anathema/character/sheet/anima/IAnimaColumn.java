package net.sf.anathema.character.sheet.anima;

import net.sf.anathema.character.core.character.ICharacter;

public interface IAnimaColumn {

  public ColumnDescriptor getDescriptor();

  public String getContent(int level, ICharacter character);
}