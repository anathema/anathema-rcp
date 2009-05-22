package net.sf.anathema.character.core.character;

public interface ICharacter extends IModelContainer, ICharacterTypeProvider {

  public ICharacterTemplate getTemplate();

  public String getDisplayName();

  public ICharacterId getId();
}