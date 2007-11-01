package net.sf.anathema.character.trait.collection;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.group.DisplayTraitGroup;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.interactive.InteractiveFavorization;
import net.sf.anathema.character.trait.interactive.InteractiveTrait;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public final class TraitGroupToDisplayTraitGroupTransformer implements ITransformer<ITraitGroup, IDisplayTraitGroup> {

  private final ITraitCollectionContext context;
  private final IFavorizationHandler favorizationHandler;
  private final ITraitPreferences traitPreferences;

  public TraitGroupToDisplayTraitGroupTransformer(
      ITraitCollectionContext context,
      IFavorizationHandler favorizationHandler,
      ITraitPreferences traitPreferences) {
    this.context = context;
    this.favorizationHandler = favorizationHandler;
    this.traitPreferences = traitPreferences;
  }

  @Override
  public IDisplayTraitGroup<IInteractiveTrait> transform(ITraitGroup group) {
    DisplayTraitGroup<IInteractiveTrait> displayGroup = new DisplayTraitGroup<IInteractiveTrait>(group.getId());
    for (String traitId : group.getTraitIds()) {
      IBasicTrait trait = context.getCollection().getTrait(traitId);
      IExperience experience = context.getExperience();
      ITraitTemplate traitTemplate = context.getTraitTemplate();
      InteractiveFavorization favorization = new InteractiveFavorization(trait, experience, favorizationHandler);
      displayGroup.addTrait(new InteractiveTrait(trait, experience, favorization, traitTemplate, traitPreferences));
    }
    return displayGroup;
  }
}