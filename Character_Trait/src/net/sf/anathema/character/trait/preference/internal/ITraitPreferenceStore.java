package net.sf.anathema.character.trait.preference.internal;

import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.character.trait.preference.ITraitPreferences;

public interface ITraitPreferenceStore extends ITraitPreferences {

  public void commitChanges();

  public void setExperienceTreatment(ExperienceTraitTreatment data);

  public void restoreDefaults();
}