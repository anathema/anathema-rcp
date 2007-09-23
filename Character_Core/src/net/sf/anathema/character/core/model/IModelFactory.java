package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.template.ICharacterTemplate;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IModelFactory extends IExecutableExtension {

  public IModel create(IContentHandle modelContent, ICharacterTemplate template) throws PersistenceException, CoreException;
}