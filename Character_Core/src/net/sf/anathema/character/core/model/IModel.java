package net.sf.anathema.character.core.model;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.IItem;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IModel extends IItem, IExecutableExtension {

  public void removeChangeListener(IChangeListener modelChangeListener);

  public void addChangeListener(IChangeListener modelChangeListener);
}