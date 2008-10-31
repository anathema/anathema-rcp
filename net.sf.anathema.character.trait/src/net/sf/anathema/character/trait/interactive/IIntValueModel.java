package net.sf.anathema.character.trait.interactive;

import net.disy.commons.core.model.IChangeableModel;

public interface IIntValueModel extends IChangeableModel {

  public int getValue();

  public void setValue(int value);
}