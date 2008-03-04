package net.sf.anathema.character.abilities.points;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.points.trait.ExperienceHandler;

public class ExperiencePointHandler extends ExperienceHandler {

  public ExperiencePointHandler() {
    super(IAbilitiesPluginConstants.MODEL_ID, 2, 3);
  }
}