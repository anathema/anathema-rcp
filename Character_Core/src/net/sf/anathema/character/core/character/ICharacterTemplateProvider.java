package net.sf.anathema.character.core.character;


public interface ICharacterTemplateProvider {

  public ICharacterTemplate getTemplate(ICharacterId characterId);

  public boolean isTemplateAvailable(ICharacterId characterId);

  public ICharacterTemplate getTemplate(String templateId);
}