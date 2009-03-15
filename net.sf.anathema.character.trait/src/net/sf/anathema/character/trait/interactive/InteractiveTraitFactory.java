package net.sf.anathema.character.trait.interactive;

import java.util.List;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.validator.IValidator;

public class InteractiveTraitFactory {

  private final IExperience experience;
  private final ITraitPreferences traitPreferences;
  private final IFavorizationInteraction favorizationInteraction;
  private final IEditorInputConfiguration configuration;

  public InteractiveTraitFactory(
      ITraitPreferences traitPreferences,
      IExperience experience,
      IEditorInputConfiguration configuration,
      IFavorizationInteraction favorizationInteraction) {
    this.traitPreferences = traitPreferences;
    this.experience = experience;
    this.configuration = configuration;
    this.favorizationInteraction = favorizationInteraction;
  }

  public IInteractiveTrait create(IBasicTrait trait, List<IValidator> validators) {
    InteractiveFavorization favorization = new InteractiveFavorization(trait, experience, favorizationInteraction);
    int maximum = configuration.getTraitMaximum(trait.getTraitType());
    return new InteractiveTrait(trait, experience, favorization, validators, traitPreferences, maximum);
  }
}