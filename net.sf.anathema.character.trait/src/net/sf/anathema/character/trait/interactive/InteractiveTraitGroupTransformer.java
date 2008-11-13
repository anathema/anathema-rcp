package net.sf.anathema.character.trait.interactive;

import java.util.List;

import net.sf.anathema.character.core.character.IModelContainer;
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

  public InteractiveTraitGroupTransformer(
      ITraitCollectionContext context,
      IFavorizationInteraction favorizationHandler,
      ITraitPreferences traitPreferences) {
    super(context);
    this.favorizationHandler = favorizationHandler;
    this.traitPreferences = traitPreferences;
  }

  @Override
  protected InteractiveTrait createTrait(IBasicTrait trait, IModelContainer container, List<IValidator> validators) {
    IExperience experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    InteractiveFavorization favorization = new InteractiveFavorization(trait, experience, favorizationHandler);
    return new InteractiveTrait(trait, container, favorization, validators, traitPreferences);
  }
}