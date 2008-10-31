package net.sf.anathema.character.caste.trait;

import net.sf.anathema.character.trait.status.ITraitStatus;

public class CasteStatus implements ITraitStatus {

  @Override
  public boolean isCheap() {
    return true;
  }

  @Override
  public boolean isModifiable() {
    return false;
  }
  
  @Override
  public boolean equals(Object obj) {
    return obj instanceof CasteStatus;
  }
  
  @Override
  public String toString() {
    return "Caste"; //$NON-NLS-1$
  }
}