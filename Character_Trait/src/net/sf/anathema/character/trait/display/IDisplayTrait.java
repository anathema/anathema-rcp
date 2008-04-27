package net.sf.anathema.character.trait.display;

import net.sf.anathema.lib.util.IIdentificate;

public interface IDisplayTrait {

  public int getValue();

  public int getMaximalValue();

  public IIdentificate getTraitType();

  public IDisplayFavorization getFavorization();
}