package net.sf.anathema.character.attributes.points;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.model.IPointHandler;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;

public class AttributeExperienceHandler extends AbstractExecutableExtension implements IPointHandler {

  @Override
  public int getPoints(ICharacterId characterId) {
    ModelIdentifier identifier = new ModelIdentifier(characterId, IAttributes.MODEL_ID);
    IAttributes attributes = (IAttributes) ModelCache.getInstance().getModel(identifier);
    return new AttributeExperienceCalculator(attributes).calculate();
  }  
}