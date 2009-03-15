package net.sf.anathema.character.trait.interactive;

import java.util.List;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.AbstractTraitGroupTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.validator.IValidator;

public final class InteractiveTraitGroupTransformer extends AbstractTraitGroupTransformer<IInteractiveTrait> {

  private final ITraitPreferences traitPreferences;
  private final IFavorizationInteraction favorizationHandler;
  private final int maximum;

  public InteractiveTraitGroupTransformer(
      ITraitCollectionContext context,
      IFavorizationInteraction favorizationHandler,
      ITraitPreferences traitPreferences,
      int maximum) {
    super(context);
    this.favorizationHandler = favorizationHandler;
    this.traitPreferences = traitPreferences;
    this.maximum = maximum;
  }

  @Override
  protected InteractiveTrait createTrait(IBasicTrait trait, IExperience experience, List<IValidator> validators) {
    InteractiveFavorization favorization = new InteractiveFavorization(trait, experience, favorizationHandler);
    return new InteractiveTrait(trait, experience, favorization, validators, traitPreferences, maximum);
  }
}