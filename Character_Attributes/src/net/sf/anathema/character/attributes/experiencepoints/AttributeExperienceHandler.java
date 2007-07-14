package net.sf.anathema.character.attributes.experiencepoints;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.attributes.model.IAttributes;
import net.sf.anathema.character.core.model.IPointHandler;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelIdentifier;

import org.eclipse.core.resources.IFolder;

public class AttributeExperienceHandler extends AbstractExecutableExtension implements IPointHandler {

  @Override
  public int getPoints(IFolder folder) {
    ModelIdentifier identifier = new ModelIdentifier(folder, IAttributes.MODEL_ID);
    IAttributes attributes = (IAttributes) ModelCache.getInstance().getModel(identifier);
    return new AttributeExperienceCalculator(attributes).calculate();
  }  
}