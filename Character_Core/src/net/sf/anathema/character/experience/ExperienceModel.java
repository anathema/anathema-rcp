package net.sf.anathema.character.experience;

import net.sf.anathema.character.core.model.AbstractModel;

public final class ExperienceModel extends AbstractModel implements IExperience {
  @Override
  public boolean isExperienced() {
    return false;
  }
}