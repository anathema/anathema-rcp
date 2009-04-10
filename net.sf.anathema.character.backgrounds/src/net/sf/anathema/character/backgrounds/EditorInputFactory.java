package net.sf.anathema.character.backgrounds;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;

public class EditorInputFactory extends UnconfiguredExecutableExtension implements IEditorInputFactory {

  @Override
  public IEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      URL imageUrl,
      IDisplayNameProvider nameProvider,
      IModelCollection modelProvider) throws PersistenceException, CoreException {
    ModelIdentifier identifier = new ModelIdentifier(characterId, IBackgroundModel.MODEL_ID);
    IBackgroundModel background = (IBackgroundModel) modelProvider.getModel(identifier);
    ICharacterType characterType = new CharacterTypeFinder().getCharacterType(characterId);
    return new BackgroundEditorInput(modelFile, imageUrl, nameProvider, characterType, background);
  }
}