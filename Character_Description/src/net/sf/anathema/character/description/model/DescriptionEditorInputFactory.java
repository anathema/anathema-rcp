package net.sf.anathema.character.description.model;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelIdentifier;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;

public class DescriptionEditorInputFactory implements IEditorInputFactory {

  @Override
  public IEditorInput create(
      IFile modelFile,
      IFolder characterFolder,
      ImageDescriptor descriptor,
      IDisplayNameProvider nameProvider,
      IModelProvider modelProvider) throws PersistenceException, CoreException {
    ModelIdentifier identifier = new ModelIdentifier(characterFolder, ICharacterDescription.MODEL_ID);
    ICharacterDescription description = (ICharacterDescription) modelProvider.getModel(identifier);
    return new CharacterDescriptionEditorInput(modelFile, descriptor, description);
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}