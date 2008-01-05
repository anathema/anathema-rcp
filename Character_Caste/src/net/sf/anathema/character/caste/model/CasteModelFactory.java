package net.sf.anathema.character.caste.model;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.model.IModelFactory;

import org.eclipse.core.runtime.CoreException;

public class CasteModelFactory extends AbstractExecutableExtension implements IModelFactory {

  @Override
  public IModel create(IContentHandle modelContent, ICharacterTemplate template) throws CoreException {
    return new CasteModel();
  }
}