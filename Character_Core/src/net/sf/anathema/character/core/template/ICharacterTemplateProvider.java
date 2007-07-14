package net.sf.anathema.character.core.template;

import net.sf.anathema.character.core.model.ICharacterId;

public interface ICharacterTemplateProvider {
  
  public ICharacterTemplate getTemplate(ICharacterId characterId);

  public boolean isTemplateAvailable(ICharacterId characterId);
}