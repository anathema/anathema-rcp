package net.sf.anathema.character.trait.collection;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.DisplayFavorization;
import net.sf.anathema.character.trait.DisplayTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.group.DisplayTraitGroup;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public final class TraitGroupToDisplayTraitGroupTransformer implements ITransformer<ITraitGroup, IDisplayTraitGroup> {

  private final ITraitCollectionContext context;
  private final IFavorizationHandler favorizationHandler;

  public TraitGroupToDisplayTraitGroupTransformer(ITraitCollectionContext context, IFavorizationHandler favorizationHandler) {
    this.context = context;
    this.favorizationHandler = favorizationHandler;
  }

  @Override
  public IDisplayTraitGroup transform(ITraitGroup group) {
    DisplayTraitGroup displayGroup = new DisplayTraitGroup(group.getId());
    for (String traitId : group.getTraitIds()) {
      IBasicTrait trait = context.getCollection().getTrait(traitId);
      IExperience experience = context.getExperience();
      ITraitTemplate traitTemplate = context.getTraitTemplate();
      DisplayFavorization favorization = new DisplayFavorization(trait, experience, favorizationHandler);
      displayGroup.addTrait(new DisplayTrait(trait, experience, favorization, traitTemplate));
    }
    return displayGroup;
  }
}