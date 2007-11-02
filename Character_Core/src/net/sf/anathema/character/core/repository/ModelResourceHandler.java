package net.sf.anathema.character.core.repository;

import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.IModelResourceHandler;
import net.sf.anathema.character.core.model.ModelExtensionPoint;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;

public class ModelResourceHandler implements IModelResourceHandler {

  @Override
  public IResource getResource(IModelIdentifier identifier) {
    IModelDisplayConfiguration displayConfiguration = new ModelExtensionPoint().getDisplayConfiguration(identifier.getModelId());
    IContainer container = (IContainer) identifier.getCharacterId().getAdapter(IContainer.class);
    return displayConfiguration.getModelFile(container);
  }
}