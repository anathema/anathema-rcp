package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.AbstractTraitGroupTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;

public class DisplayTraitGroupTransformer extends AbstractTraitGroupTransformer<IDisplayTrait> {

  private final DisplayTraitFactory displayTraitFactory;

  public DisplayTraitGroupTransformer(ITraitCollectionContext context, IFavorizationInteraction favorizationHandler) {
    super(context.getCollection());
    this.displayTraitFactory = new DisplayTraitFactory(context, favorizationHandler);
  }

  @Override
  protected IDisplayTrait createTrait(IBasicTrait trait) {
    return displayTraitFactory.createTrait(trait);
  }
}