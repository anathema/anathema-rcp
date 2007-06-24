package net.sf.anathema.character.basics;

import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.character.core.model.IModelFactory;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class BasicsFactory implements IModelFactory {

  @Override
  public IModel create(IFolder characterFolder) {
    return new CharacterBasics();
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}