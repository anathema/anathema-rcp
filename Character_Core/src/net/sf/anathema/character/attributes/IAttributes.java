package net.sf.anathema.character.attributes;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.character.core.trait.ITrait;

public interface IAttributes extends IItem {

  public ITrait[] getTraits();
}