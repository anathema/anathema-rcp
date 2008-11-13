package net.sf.anathema.character.trait.display;

import java.util.List;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.AbstractTraitGroupTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.validator.IValidator;

public class DisplayTraitGroupTransformer extends AbstractTraitGroupTransformer<IDisplayTrait> {

  private final IFavorizationInteraction favorizationHandler;

  public DisplayTraitGroupTransformer(ITraitCollectionContext context, IFavorizationInteraction favorizationHandler) {
    super(context);
    this.favorizationHandler = favorizationHandler;
  }

  @Override
  protected IDisplayTrait createTrait(IBasicTrait trait, IExperience experience, List<IValidator> validators) {
    return new DisplayTrait(new DisplayFavorization(favorizationHandler, trait), trait, experience, 10);
  }
}