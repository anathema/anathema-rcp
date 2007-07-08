package net.sf.anathema.character.experiencepoints;

public class NullExperiencePointViewInput implements IExperiencePointViewInput {

  private IExperiencePointEntry[] experiencePointEntries = new IExperiencePointEntry[0];

  @Override
  public IExperiencePointEntry[] getEntries() {
    return experiencePointEntries;
  }
}