package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.core.character.ICharacterTemplate;

public class ModelDescriptor implements IModelDescriptor {

  private final IExtensionElement modelElement;

  public ModelDescriptor(IExtensionElement modelElement) {
    this.modelElement = modelElement;
  }

  public boolean isSupportedFor(ICharacterTemplate template) {
    return template.supportsModel(modelElement.getAttribute("id"));
  }
}