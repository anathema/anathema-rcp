package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.collection.AbstractTraitGroupTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public class DisplayTraitGroupTransformer extends AbstractTraitGroupTransformer<IDisplayTrait> {

  private final IFavorizationHandler favorizationHandler;

  public DisplayTraitGroupTransformer(ITraitCollectionContext context, IFavorizationHandler favorizationHandler) {
    super(context);
    this.favorizationHandler = favorizationHandler;
  }

  @Override
  protected IDisplayTrait createTrait(IBasicTrait trait, IExperience experience, ITraitTemplate traitTemplate) {
    return new DisplayTrait(favorizationHandler, trait, experience, traitTemplate);
  }
}