package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;

public class DisplayTraitFactory {

  private final IFavorizationInteraction favorizationHandler;
  private final ITraitCollectionContext context;

  public DisplayTraitFactory(ITraitCollectionContext context, IFavorizationInteraction favorizationHandler) {
    this.context = context;
    this.favorizationHandler = favorizationHandler;
  }

  public IDisplayTrait createTrait(IBasicTrait trait) {
    IExperience experience = context.getExperience();
    DisplayFavorization favorization = new DisplayFavorization(favorizationHandler, trait);
    return new DisplayTrait(favorization, trait, experience, 10);
  }
}