package net.sf.anathema.character.trait.favorization;

public interface ITraitFavorization extends ICasteProvider {

  public void addFavorableStateChangedListener(IFavorableStateChangedListener listener);

  public FavorableState getFavorableState();

  public boolean isCaste();

  public boolean isCasteOrFavored();

  public boolean isFavored();

  public void setFavorableState(FavorableState state);

  public void setFavored(boolean favored);

  public void updateFavorableStateToCaste();

  public int getMinimalValue();

  public void ensureMinimalValue();
}