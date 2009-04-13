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

  private final ITraitCollectionContext context;
  private final InteractiveTraitFactory factory;

  public InteractiveTraitGroupTransformer(
      ITraitCollectionContext context,
      IFavorizationInteraction favorizationInteraction,
      ITraitPreferences traitPreferences,
      IEditorInputConfiguration configuration) {
    super(context.getCollection());
    IExperience experience = context.getExperience();
    this.context = context;
    this.factory = new InteractiveTraitFactory(traitPreferences, experience, configuration, favorizationInteraction);
  }

  @Override
  public IInteractiveTrait createTrait(IBasicTrait trait) {
    List<IValidator> validators = context.getValidators(trait.getTraitType().getId());
    return factory.create(trait, validators);
  }
}