package net.sf.anathema.character.core.template;

public interface ICharacterTemplate {

  public String getId();

  public String getUnnamedLabel();

  public boolean supportsModel(String modelId);
}