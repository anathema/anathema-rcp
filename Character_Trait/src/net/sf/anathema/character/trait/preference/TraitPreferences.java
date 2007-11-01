package net.sf.anathema.character.trait.preference;

import org.eclipse.jface.preference.IPreferenceStore;

public class TraitPreferences implements ITraitPreferences {

  public static final String PREF_TRAIT_EXPERIENCE_TREATMENT = "trait.experienceTreatment"; //$NON-NLS-1$
  private final IPreferenceStore store;
  private ExperienceTraitTreatment treatment;

  public TraitPreferences(IPreferenceStore store) {
    this.store = store;
  }

  @Override
  public ExperienceTraitTreatment getExperienceTreatment() {
    if (treatment != null) {
      return treatment;
    }
    return ExperienceTraitTreatment.valueOf(store.getString(PREF_TRAIT_EXPERIENCE_TREATMENT));
  }

  public void setExperienceTreatment(ExperienceTraitTreatment treatment) {
    this.treatment = treatment;
  }

  public void commitChanges() {
    if (treatment != null) {
      store.setValue(PREF_TRAIT_EXPERIENCE_TREATMENT, treatment.name());
    }
  }
}