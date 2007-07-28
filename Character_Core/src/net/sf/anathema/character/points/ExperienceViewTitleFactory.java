package net.sf.anathema.character.points;

import net.sf.anathema.character.experience.IExperience;

public class ExperienceViewTitleFactory {

  private final IExperienceProvider provider;

  public ExperienceViewTitleFactory(IExperienceProvider provider) {
    this.provider = provider;
  }

  public String create() {
    IExperience experience = provider.getExperience();
    if (experience == null) {
      return "Character Points";
    }
    return experience.isExperienced() ? "Experience Points" : "Bonus Points";
  }
}