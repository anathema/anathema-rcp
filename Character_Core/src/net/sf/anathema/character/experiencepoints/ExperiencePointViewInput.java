package net.sf.anathema.character.experiencepoints;

public class ExperiencePointViewInput implements IExperiencePointViewInput {

  private int currentTimeMillis = (int) System.currentTimeMillis();

  public IExperiencePointEntry[] getEntries() {
    return new IExperiencePointEntry[] { new IExperiencePointEntry() {

      @Override
      public String getModelDisplayName() {
        return "Hasäntum";
      }

      @Override
      public int getExperiencePoints() {
        return 5;
      }
    }, new IExperiencePointEntry() {

      @Override
      public String getModelDisplayName() {
        return "Lamapuit";
      }

      @Override
      public int getExperiencePoints() {
        return currentTimeMillis;
      }
    } };
  }
}