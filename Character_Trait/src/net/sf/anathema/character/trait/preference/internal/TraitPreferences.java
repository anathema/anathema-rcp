package net.sf.anathema.character.trait.preference.internal;

import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;

import org.eclipse.jface.preference.IPreferenceStore;

public class TraitPreferences implements ITraitPreferenceStore {

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
    String value = store.getString(PREF_TRAIT_EXPERIENCE_TREATMENT);
    return ExperienceTraitTreatment.valueOf(value);
  }

  public void setExperienceTreatment(ExperienceTraitTreatment treatment) {
    this.treatment = treatment;
  }

  public void commitChanges() {
    if (treatment != null) {
      store.setValue(PREF_TRAIT_EXPERIENCE_TREATMENT, treatment.name());
    }
  }

  public void initializeDefaults() {
    store.setDefault(PREF_TRAIT_EXPERIENCE_TREATMENT, ExperienceTraitTreatment.LeaveUnchanged.name());
  }

  @Override
  public void restoreDefaults() {
    treatment = null;
    store.setValue(PREF_TRAIT_EXPERIENCE_TREATMENT, store.getDefaultString(PREF_TRAIT_EXPERIENCE_TREATMENT));
  }
}