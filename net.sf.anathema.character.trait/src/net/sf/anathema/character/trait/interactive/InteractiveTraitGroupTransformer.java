package net.sf.anathema.character.trait.interactive;

import java.util.List;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.AbstractTraitGroupTransformer;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.validator.IValidator;

public final class InteractiveTraitGroupTransformer extends AbstractTraitGroupTransformer<IInteractiveTrait> {

  private final ITraitPreferences traitPreferences;
  private final IFavorizationInteraction favorizationHandler;
  private final IEditorInputConfiguration configuration;
  private final ITraitCollectionContext context;

  public InteractiveTraitGroupTransformer(
      ITraitCollectionContext context,
      IFavorizationInteraction favorizationInteraction,
      ITraitPreferences traitPreferences,
      IEditorInputConfiguration configuration) {
    super(context.getCollection());
    this.context = context;
    this.favorizationHandler = favorizationInteraction;
    this.traitPreferences = traitPreferences;
    this.configuration = configuration;
  }

  @Override
  public IInteractiveTrait createTrait(IBasicTrait trait) {
    IExperience experience = context.getExperience();
    List<IValidator> validators = context.getValidators(trait.getTraitType().getId());
    InteractiveTraitFactory factory = new InteractiveTraitFactory(
        traitPreferences,
        experience,
        configuration,
        favorizationHandler);
    return factory.create(trait, validators);
  }
}