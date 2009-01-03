package net.sf.anathema.charms.character;

import net.sf.anathema.charms.character.preference.ExperienceCharmTreatment;
import net.sf.anathema.charms.character.preference.ICharmPreferences;

public class DummyCharmPreferences implements ICharmPreferences {

  private ExperienceCharmTreatment treatment = ExperienceCharmTreatment.Remember;

  @Override
  public ExperienceCharmTreatment getExperienceTreatment() {
    return treatment;
  }

  public void setTreatment(ExperienceCharmTreatment treatment) {
    this.treatment = treatment;
  }
}