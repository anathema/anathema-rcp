package net.sf.anathema.character.trait.favorization;

import net.sf.anathema.character.trait.IDisplayTrait;
import net.sf.anathema.lib.collection.IClosure;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.util.IIdentificate;

public class TraitFavorization implements ITraitFavorization {

  private FavorableState state;
  private final GenericControl<IFavorableStateChangedListener> favorableStateControl = new GenericControl<IFavorableStateChangedListener>();
  private final IIncrementChecker favoredIncrementChecker;
  private final IDisplayTrait trait;
  private final IIdentificate caste;
  private final boolean isRequiredFavored;
  private final ICasteProvider basicData;

  public TraitFavorization(
      ICasteProvider basicData,
      IIdentificate caste,
      IIncrementChecker favoredIncrementChecker,
      IDisplayTrait trait,
      boolean isRequiredFavored) {
    this.basicData = basicData;
    this.caste = caste;
    this.favoredIncrementChecker = favoredIncrementChecker;
    this.trait = trait;
    this.isRequiredFavored = isRequiredFavored;
    this.state = isRequiredFavored ? FavorableState.Favored : FavorableState.Default;
  }

  public final void setFavorableState(FavorableState state) {
    FavorableState newState = state;
    if (newState == FavorableState.Caste && isRequiredFavored) {
      throw new IllegalStateException("Traits with required favored must not  be of any caste"); //$NON-NLS-1$
    }
    if (this.state == newState) {
      return;
    }
    if (this.state == FavorableState.Caste && newState == FavorableState.Favored) {
      return;
    }
    if (newState == FavorableState.Favored && !favoredIncrementChecker.isValidIncrement(1)) {
      return;
    }
    if (isRequiredFavored && newState == FavorableState.Default) {
      newState = FavorableState.Favored;
    }
    this.state = newState;
    ensureMinimalValue();
    fireFavorableStateChangedEvent();
  }

  public void ensureMinimalValue() {
    final int minimalValue = getMinimalValue();
    if (trait.getValue() < minimalValue) {
      // TODO Aggregated Trait behandeln
      trait.setValue(minimalValue);
    }
  }

  public int getMinimalValue() {
    return this.state == FavorableState.Favored ? 1 : 0;
  }

  public void setFavored(boolean favored) {
    if (isCaste() || isFavored() == favored) {
      return;
    }
    setFavorableState(favored ? FavorableState.Favored : FavorableState.Default);
  }

  public void setCaste(boolean caste) {
    if (isCaste() == caste) {
      return;
    }
    setFavorableState(caste ? FavorableState.Caste : (isCaste() ? FavorableState.Default : FavorableState.Favored));
  }

  public final FavorableState getFavorableState() {
    return state;
  }

  public final void addFavorableStateChangedListener(IFavorableStateChangedListener listener) {
    favorableStateControl.addListener(listener);
  }

  private final void fireFavorableStateChangedEvent() {
    favorableStateControl.forAllDo(new IClosure<IFavorableStateChangedListener>() {
      public void execute(IFavorableStateChangedListener input) {
        input.favorableStateChanged(state);
      }
    });
  }

  public final boolean isFavored() {
    return state == FavorableState.Favored;
  }

  public final boolean isCaste() {
    return state == FavorableState.Caste;
  }

  public final boolean isCasteOrFavored() {
    return isCaste() || isFavored();
  }

  public IIdentificate getCaste() {
    return caste;
  }

  public void updateFavorableStateToCaste() {
    IIdentificate casteType = basicData.getCaste();
    if (isCaste() != isSupportedCasteType(casteType)) {
      setCaste(isSupportedCasteType(casteType));
    }
  }

  private boolean isSupportedCasteType(IIdentificate casteType) {
    IIdentificate favorizationCaste = getCaste();
    return favorizationCaste != null && favorizationCaste == casteType;
  }
}