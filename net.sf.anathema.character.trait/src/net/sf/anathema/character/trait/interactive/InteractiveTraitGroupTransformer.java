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

  public InteractiveTraitGroupTransformer(
      ITraitCollectionContext context,
      IFavorizationInteraction favorizationHandler,
      ITraitPreferences traitPreferences,
      IEditorInputConfiguration configuration) {
    super(context);
    this.favorizationHandler = favorizationHandler;
    this.traitPreferences = traitPreferences;
    this.configuration = configuration;
  }

  @Override
  protected IInteractiveTrait createTrait(IBasicTrait trait, IExperience experience, List<IValidator> validators) {
    InteractiveTraitFactory factory = new InteractiveTraitFactory(traitPreferences, trait, experience, validators);
    return factory.create(configuration, favorizationHandler);
  }
}