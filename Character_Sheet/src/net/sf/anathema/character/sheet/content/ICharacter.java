package net.sf.anathema.character.sheet.content;

import net.sf.anathema.character.core.model.IModel;

public interface ICharacter {

  public IModel getModel(String modelId);
}