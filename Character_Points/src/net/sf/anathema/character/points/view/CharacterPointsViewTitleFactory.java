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
      return Messages.CharacterPointsViewTitleFactory_NeutralTitle;
    }
    return experience.isExperienced()
        ? Messages.CharacterPointsViewTitleFactory_ExperiencedTitle
        : Messages.CharacterPointsViewTitleFactory_InexperiencedTitle;
  }
}