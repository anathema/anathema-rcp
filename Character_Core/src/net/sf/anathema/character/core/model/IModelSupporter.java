package net.sf.anathema.character.core.model;

import net.sf.anathema.character.core.character.ICharacterTemplate;

public interface IModelSupporter {

  public boolean isSupportedBy(ICharacterTemplate template);

}