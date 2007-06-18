package net.sf.anathema.character.attributes;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.character.trait.IBasicTrait;

public interface IAttributes extends IItem {

  public IBasicTrait[] getTraits();

  public IBasicTrait getTrait(String id);
}