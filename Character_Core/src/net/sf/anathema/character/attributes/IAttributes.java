package net.sf.anathema.character.attributes;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.character.trait.ITrait;

public interface IAttributes extends IItem {

  public ITrait[] getTraits();

  public ITrait getTrait(String id);
}