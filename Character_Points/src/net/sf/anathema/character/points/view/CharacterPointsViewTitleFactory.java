package net.sf.anathema.character.points.view;

import net.sf.anathema.character.experience.IExperience;

public class CharacterPointsViewTitleFactory {

  private final IExperienceProvider provider;

  public CharacterPointsViewTitleFactory(IExperienceProvider provider) {
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