package net.sf.anathema.character.core.character;

public interface ICharacterTemplate {

  public String getId();

  public String getName();

  public String getCharacterTypeId();

  public boolean supportsModel(String modelId);
}