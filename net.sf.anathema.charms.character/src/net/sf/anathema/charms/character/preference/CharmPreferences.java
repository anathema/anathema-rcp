package net.sf.anathema.charms.character.preference;

import org.eclipse.jface.preference.IPreferenceStore;

public class CharmPreferences implements ICharmPreferenceStore {

  public static final String PREF_CHARM_EXPERIENCE_TREATMENT = "charm.experienceTreatment"; //$NON-NLS-1$
  private final IPreferenceStore store;
  private ExperienceCharmTreatment treatment;

  public CharmPreferences(IPreferenceStore store) {
    this.store = store;
  }

  @Override
  public ExperienceCharmTreatment getExperienceTreatment() {
    if (treatment != null) {
      return treatment;
    }
    String value = store.getString(PREF_CHARM_EXPERIENCE_TREATMENT);
    return ExperienceCharmTreatment.valueOf(value);
  }

  @Override
  public void setExperienceTreatment(ExperienceCharmTreatment data) {
    this.treatment = data;
  }

  public void commitChanges() {
    if (treatment != null) {
      store.setValue(PREF_CHARM_EXPERIENCE_TREATMENT, treatment.name());
    }
  }

  public void initializeDefaults() {
    store.setDefault(PREF_CHARM_EXPERIENCE_TREATMENT, ExperienceCharmTreatment.Remember.name());
  }

  @Override
  public void restoreDefaults() {
    treatment = null;
    store.setValue(PREF_CHARM_EXPERIENCE_TREATMENT, store.getDefaultString(PREF_CHARM_EXPERIENCE_TREATMENT));
  }
}