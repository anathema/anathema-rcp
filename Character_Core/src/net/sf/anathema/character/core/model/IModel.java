package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.lib.control.change.IChangeListener;

import org.eclipse.core.runtime.IExecutableExtension;

public interface IModel extends IItem, IExecutableExtension {

  public void removeChangeListener(IChangeListener modelChangeListener);

  public void addChangeListener(IChangeListener modelChangeListener);
}