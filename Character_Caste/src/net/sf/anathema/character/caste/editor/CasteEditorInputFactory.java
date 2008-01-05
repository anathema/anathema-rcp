package net.sf.anathema.character.caste.editor;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;

public class CasteEditorInputFactory extends AbstractExecutableExtension implements IEditorInputFactory {

  @Override
  public IEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      URL imageUrl,
      IDisplayNameProvider nameProvider,
      IModelCollection modelProvider) throws PersistenceException, CoreException {
    return new CasteEditorInput(modelFile, imageUrl, nameProvider);
  }
}