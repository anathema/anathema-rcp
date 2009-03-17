package net.sf.anathema.character.spiritualtraits.validator;

import java.util.Comparator;

import net.sf.anathema.character.trait.IBasicTrait;

public final class ReverseCreationValueComparator implements Comparator<IBasicTrait> {
  @Override
  public int compare(IBasicTrait trait1, IBasicTrait trait2) {
    return trait2.getCreationModel().getValue() - trait1.getCreationModel().getValue();
  }
}