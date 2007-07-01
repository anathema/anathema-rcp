package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.trait.IBasicTrait;

public interface IAttributes extends IModel {

  public static final String MODEL_ID = "net.sf.anathema.character.attributes.model"; //$NON-NLS-1$

  public IBasicTrait[] getTraits();

  public IBasicTrait getTrait(String id);
}