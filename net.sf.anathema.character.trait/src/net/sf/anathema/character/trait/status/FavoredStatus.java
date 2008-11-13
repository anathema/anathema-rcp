package net.sf.anathema.character.trait.status;

import net.sf.anathema.character.trait.IBasicTrait;

public class FavoredStatus implements ITraitStatus {

  public static boolean isFavored(IBasicTrait trait) {
    return trait.getStatusManager().getStatus() instanceof FavoredStatus;
  }

  @Override
  public boolean isCheap() {
    return true;
  }

  @Override
  public boolean isModifiable() {
    return true;
  }

  @Override
  public String toString() {
    return "Favored"; //$NON-NLS-1$
  }
}