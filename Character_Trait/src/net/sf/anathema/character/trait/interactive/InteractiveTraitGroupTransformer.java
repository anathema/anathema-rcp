package net.sf.anathema.character.trait.interactive;

import java.util.List;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.collection.AbstractTraitGroupTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.validator.IValidator;

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
  protected InteractiveTrait createTrait(IBasicTrait trait, IModelContainer container, List<IValidator> validators) {
    IExperience experience = (IExperience) container.getModel(IExperience.MODEL_ID);
    InteractiveFavorization favorization = new InteractiveFavorization(trait, experience, favorizationHandler);
    return new InteractiveTrait(trait, container, favorization, validators, traitPreferences);
  }
}