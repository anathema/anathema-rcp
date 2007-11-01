package net.sf.anathema.character.trait.preference;

public interface ITraitPreferences {

  public void commitChanges();

  public ExperienceTraitTreatment getExperienceTreatment();

  public void setExperienceTreatment(ExperienceTraitTreatment data);

  public void restoreDefaults();
}