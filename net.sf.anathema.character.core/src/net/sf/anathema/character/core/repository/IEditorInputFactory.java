package net.sf.anathema.character.core.repository;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.ui.IEditorInput;

public interface IEditorInputFactory extends IExecutableExtension {

  public IEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      URL imageUrl,
      IDisplayNameProvider nameProvider,
      IModelCollection modelProvider) throws PersistenceException, CoreException;
}