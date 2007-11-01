package net.sf.anathema.character.trait;

public class BasicTraitUtilities {

  public static boolean isExperiencedValueSet(IBasicTrait trait) {
    return trait.getExperiencedModel().getValue() > 0;
  }
}