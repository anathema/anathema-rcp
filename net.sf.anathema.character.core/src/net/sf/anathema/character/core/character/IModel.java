package net.sf.anathema.character.core.character;

import net.disy.commons.core.model.IChangeableModel;
import net.sf.anathema.basics.item.IItem;

public interface IModel extends IItem, IChangeableModel {

  public Object getSaveState();

  public void revertTo(Object memento);

  public void updateToDependencies();
}