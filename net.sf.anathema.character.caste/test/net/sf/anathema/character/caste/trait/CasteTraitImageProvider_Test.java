package net.sf.anathema.character.caste.trait;

import net.sf.anathema.character.trait.display.DisplayTrait;
import net.sf.anathema.character.trait.display.IDisplayFavorization;

import org.junit.Test;

public class CasteTraitImageProvider_Test {

  @Test(expected=IllegalArgumentException.class)
  public void throwsExceptionIfNonCasteStatusIsRequested() throws Exception {
    IDisplayFavorization favorization = null;
    DisplayTrait trait = new DisplayTrait(favorization, null, null, 0);
    new CasteTraitImageProvider().getImage(trait, null);
  }
}