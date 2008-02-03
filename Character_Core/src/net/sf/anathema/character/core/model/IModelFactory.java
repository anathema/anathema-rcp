package net.sf.anathema.character.core.model;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtension;

public interface IModelFactory<M extends IModel> extends IExecutableExtension {

  public M create(IContentHandle modelContent, ICharacterTemplate template, ICharacterId characterId) throws PersistenceException, CoreException;

  public IModelInitializer createInitializer(M model, IContentHandle file, IModelIdentifier identifier);
}