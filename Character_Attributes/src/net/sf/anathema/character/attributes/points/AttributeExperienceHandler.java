package net.sf.anathema.character.attributes.points;

import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.points.configuration.IPointHandler;
import net.sf.anathema.character.points.trait.ExperienceHandler;

public class AttributeExperienceHandler extends ExperienceHandler implements IPointHandler {

  public AttributeExperienceHandler() {
    super(IAttributesPluginConstants.MODEL_ID, 4, 0);
  }
}