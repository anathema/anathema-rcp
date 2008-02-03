package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IModelFactory<M extends IModel> extends IExecutableExtension {

  public IModelInitializer createInitializer(
      IContentHandle handle,
      ICharacterTemplate template,
      IModelIdentifier identifier) throws PersistenceException, CoreException;
}