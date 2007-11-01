package net.sf.anathema.character.trait.interactive;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.collection.AbstractTraitGroupTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.rules.ITraitTemplate;

public final class InteractiveTraitGroupTransformer extends AbstractTraitGroupTransformer<IInteractiveTrait> implements
    ITransformer<ITraitGroup, IDisplayTraitGroup<IInteractiveTrait>> {

  private final ITraitPreferences traitPreferences;
  private final IFavorizationHandler favorizationHandler;

  public InteractiveTraitGroupTransformer(
      ITraitCollectionContext context,
      IFavorizationHandler favorizationHandler,
      ITraitPreferences traitPreferences) {
    super(context);
    this.favorizationHandler = favorizationHandler;
    this.traitPreferences = traitPreferences;
  }

  @Override
  protected InteractiveTrait createTrait(IBasicTrait trait, IExperience experience, ITraitTemplate traitTemplate) {
    InteractiveFavorization favorization = new InteractiveFavorization(trait, experience, favorizationHandler);
    return new InteractiveTrait(trait, experience, favorization, traitTemplate, traitPreferences);
  }
}