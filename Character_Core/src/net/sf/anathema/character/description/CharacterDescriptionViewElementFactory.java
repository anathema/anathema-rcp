package net.sf.anathema.character.description;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.repository.ICharacterModelViewElementFactory;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

public class CharacterDescriptionViewElementFactory implements ICharacterModelViewElementFactory {

  @Override
  public IViewElement create(IViewElement parent, IFolder characterFolder) {
    return new CharacterDescriptionViewElement(parent, characterFolder);
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}