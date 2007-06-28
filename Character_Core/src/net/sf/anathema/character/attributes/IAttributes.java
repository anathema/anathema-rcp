package net.sf.anathema.character.attributes;

import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.trait.IBasicTrait;

public interface IAttributes extends IModel {

  public IBasicTrait[] getTraits();

  public IBasicTrait getTrait(String id);
}