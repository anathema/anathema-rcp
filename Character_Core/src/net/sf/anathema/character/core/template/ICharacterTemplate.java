package net.sf.anathema.character.core.template;

public interface ICharacterTemplate {

  public String getId();

  public boolean supportsModel(String modelId);
}