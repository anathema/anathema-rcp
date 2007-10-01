package net.sf.anathema.character.core.template;

public interface ICharacterTemplate {

  public String getId();

  public String getName();

  public String getCharacterTypeId();

  public boolean supportsModel(String modelId);
}