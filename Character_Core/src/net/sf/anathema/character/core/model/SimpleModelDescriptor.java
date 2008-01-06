package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacterTemplate;

public class SimpleModelDescriptor extends AbstractExecutableExtension implements IModelDescriptor {

  private final String modelId;

  public SimpleModelDescriptor(String modelId) {
    this.modelId = modelId;
  }
  
  @Override
  public boolean isSupportedBy(ICharacterTemplate template) {
    return template.supportsModel(modelId);
  }
}