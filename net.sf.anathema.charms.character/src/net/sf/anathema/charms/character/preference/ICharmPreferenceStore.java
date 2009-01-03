package net.sf.anathema.charms.character.preference;

public interface ICharmPreferenceStore extends ICharmPreferences {

  public void commitChanges();

  public void setExperienceTreatment(ExperienceCharmTreatment data);

  public void restoreDefaults();
}