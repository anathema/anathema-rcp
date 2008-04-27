package net.sf.anathema.character.core.character;

import net.disy.commons.core.model.IChangeableModel;
import net.sf.anathema.basics.item.IItem;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IModel extends IItem, IExecutableExtension, IChangeableModel {

  public void updateToDependencies();
}