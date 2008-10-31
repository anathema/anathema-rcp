package net.sf.anathema.character.trait.status;

public class DefaultStatus implements ITraitStatus {

  @Override
  public boolean isCheap() {
    return false;
  }

  @Override
  public boolean isModifiable() {
    return true;
  }
  
  @Override
  public String toString() {
    return "Default"; //$NON-NLS-1$
  }
}