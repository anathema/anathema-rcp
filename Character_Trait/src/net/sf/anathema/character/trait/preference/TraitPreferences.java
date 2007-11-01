package net.sf.anathema.character.trait.preference;

import org.eclipse.jface.preference.IPreferenceStore;

public class TraitPreferences implements ITraitPreferences {

  public static final String PREF_TRAIT_EXPERIENCE_TREATMENT = "trait.experienceTreatment"; //$NON-NLS-1$
  private final IPreferenceStore store;

  public TraitPreferences(IPreferenceStore store) {
    this.store = store;
  }
  
  @Override
  public ExperienceTraitTreatment getExperienceTreatment() {
    // TODO Auto-generated method stub
    return null;
  }
  
  public void setExperienceTreatment(ExperienceTraitTreatment treatment) {
    
  }

  public void commitChanges() {
    // TODO Auto-generated method stub
    
  }
}