package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.AbstractTraitGroupTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;

public class DisplayTraitGroupTransformer extends AbstractTraitGroupTransformer<IDisplayTrait> {

  private final IFavorizationInteraction favorizationHandler;
  private final ITraitCollectionContext context;

  public DisplayTraitGroupTransformer(ITraitCollectionContext context, IFavorizationInteraction favorizationHandler) {
    super(context.getCollection());
    this.context = context;
    this.favorizationHandler = favorizationHandler;
  }

  @Override
  protected IDisplayTrait createTrait(IBasicTrait trait) {
    IExperience experience = context.getExperience();
    DisplayFavorization favorization = new DisplayFavorization(favorizationHandler, trait);
    return new DisplayTrait(favorization, trait, experience, 10);
  }
}