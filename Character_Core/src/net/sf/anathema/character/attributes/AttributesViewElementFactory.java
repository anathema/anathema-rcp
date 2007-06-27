package net.sf.anathema.character.attributes;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;
import net.sf.anathema.character.core.repository.ICharacterModelViewElementFactory;
import net.sf.anathema.character.core.repository.ModelConfiguration;
import net.sf.anathema.character.core.repository.ModelViewElement;

public class AttributesViewElementFactory implements ICharacterModelViewElementFactory {

  @Override
  public IViewElement create(IViewElement parent, IFolder characterFolder) {
    return new ModelViewElement(parent, characterFolder, new ModelConfiguration());
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}