package net.sf.anathema.character.trait.favorization;

import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class NullTraitFavorization implements ITraitFavorization {

  public void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    // nothing to do
  }

  public IIdentificate getCaste() {
    return new Identificate(null);
  }

  public FavorableState getFavorableState() {
    return FavorableState.Default;
  }

  public boolean isCaste() {
    return false;
  }

  public boolean isCasteOrFavored() {
    return false;
  }

  public boolean isFavored() {
    return false;
  }

  public void setFavorableState(FavorableState state) {
    // nothing to do
  }

  public void setFavored(boolean favored) {
    // nothing to do
  }

  public void updateFavorableStateToCaste() {
    // nothing to do
  }

  public int getMinimalValue() {
    return 0;
  }

  public void ensureMinimalValue() {
    // nothing to do
  }
}