package net.sf.anathema.character.description;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelFactory;

public class DescriptionModelFactory implements IModelFactory {

  @Override
  public IModel create(IFolder characterFolder) {
    //TODO Implement 
    return null;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}