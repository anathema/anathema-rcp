package net.sf.anathema.character.description;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.IEditorInput;

public class DescriptionEditorInputFactory implements IEditorInputFactory {

  @Override
  public IEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      URL imageUrl,
      IDisplayNameProvider nameProvider,
      IModelCollection modelProvider) throws PersistenceException, CoreException {
    ModelIdentifier identifier = new ModelIdentifier(characterId, ICharacterDescription.MODEL_ID);
    ICharacterDescription description = (ICharacterDescription) modelProvider.getModel(identifier);
    return new CharacterDescriptionEditorInput(modelFile, imageUrl, description);
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}