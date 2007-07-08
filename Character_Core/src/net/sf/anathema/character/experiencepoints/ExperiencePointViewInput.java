package net.sf.anathema.character.experiencepoints;

import org.eclipse.core.resources.IFolder;

public class ExperiencePointViewInput implements IExperiencePointViewInput {

  private int currentTimeMillis = (int) System.currentTimeMillis();
  private final IFolder folder;

  public ExperiencePointViewInput(IFolder folder) {
    this.folder = folder;
  }

  @Override
  public IFolder getFolder() {
    return folder;
  }

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