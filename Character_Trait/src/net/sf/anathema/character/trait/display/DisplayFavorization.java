package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;

public class DisplayFavorization implements IDisplayFavorization {

  private final IFavorizationHandler favorizationHandler;
  private final IBasicTrait basicTrait;

  public DisplayFavorization(IFavorizationHandler favorizationHandler, IBasicTrait basicTrait) {
    this.favorizationHandler = favorizationHandler;
    this.basicTrait = basicTrait;
  }

  @Override
  public boolean isFavorable() {
    return favorizationHandler.isFavorable();
  }

  @Override
  public boolean isFavored() {
    return basicTrait.getFavoredModel().getValue();
  }
  
  protected final IBasicTrait getBasicTrait() {
    return basicTrait;
  }
  
  protected final IFavorizationHandler getFavorizationHandler() {
    return favorizationHandler;
  }
}