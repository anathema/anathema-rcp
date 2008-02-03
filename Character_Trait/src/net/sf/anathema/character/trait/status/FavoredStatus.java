package net.sf.anathema.character.trait.status;

public class FavoredStatus implements ITraitStatus {

  @Override
  public boolean isCheap() {
    return true;
  }

  @Override
  public boolean isModifiable() {
    return true;
  }
}