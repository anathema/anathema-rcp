package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.character.trait.status.ITraitStatus;

public class DisplayFavorization implements IDisplayFavorization {

  private final IFavorizationInteraction favorizationHandler;
  private final IBasicTrait basicTrait;

  public DisplayFavorization(IFavorizationInteraction favorizationHandler, IBasicTrait basicTrait) {
    this.favorizationHandler = favorizationHandler;
    this.basicTrait = basicTrait;
  }

  @Override
  public boolean isFavorable() {
    return favorizationHandler.isFavorable();
  }

  @Override
  public boolean isFavored() {
    return getStatus() instanceof FavoredStatus;
  }

  @Override
  public ITraitStatus getStatus() {
     return basicTrait.getStatusManager().getStatus();
  }

  protected final IBasicTrait getBasicTrait() {
    return basicTrait;
  }

  protected final IFavorizationInteraction getFavorizationInteraction() {
    return favorizationHandler;
  }
}