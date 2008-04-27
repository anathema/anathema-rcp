package net.sf.anathema.character.trait.display;

import java.util.List;

import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.collection.AbstractTraitGroupTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.validator.IValidator;

public class DisplayTraitGroupTransformer extends AbstractTraitGroupTransformer<IDisplayTrait> {

  private final IFavorizationHandler favorizationHandler;

  public DisplayTraitGroupTransformer(ITraitCollectionContext context, IFavorizationHandler favorizationHandler) {
    super(context);
    this.favorizationHandler = favorizationHandler;
  }

  @Override
  protected IDisplayTrait createTrait(IBasicTrait trait, IModelContainer container, List<IValidator> validators) {
    return new DisplayTrait(new DisplayFavorization(favorizationHandler, trait), trait, container, 10);
  }
}