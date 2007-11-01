package net.sf.anathema.character.trait.display;

import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.character.trait.preference.ITraitPreferences;

public class DummyTraitPreferences implements ITraitPreferences {

  private final ExperienceTraitTreatment treatment;

  public DummyTraitPreferences(ExperienceTraitTreatment treatment) {
    this.treatment = treatment;
  }
  
  @Override
  public ExperienceTraitTreatment getExperienceTreatment() {
    return treatment;
  }
}