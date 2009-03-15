package net.sf.anathema.character.trait.interactive;

import java.util.List;

import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.groupeditor.IEditorInputConfiguration;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.character.trait.validator.IValidator;

public class InteractiveTraitFactory {

  private final IBasicTrait trait;
  private final IExperience experience;
  private final List<IValidator> validators;
  private final ITraitPreferences traitPreferences;

  public InteractiveTraitFactory(
      ITraitPreferences traitPreferences,
      IBasicTrait trait,
      IExperience experience,
      List<IValidator> validators) {
    this.traitPreferences = traitPreferences;
    this.trait = trait;
    this.experience = experience;
    this.validators = validators;
  }

  public IInteractiveTrait create(
      IEditorInputConfiguration configuration,
      IFavorizationInteraction favorizationInteraction) {
    InteractiveFavorization favorization = new InteractiveFavorization(trait, experience, favorizationInteraction);
    int maximum = configuration.getTraitMaximum(trait.getTraitType());
    return new InteractiveTrait(trait, experience, favorization, validators, traitPreferences, maximum);
  }
}