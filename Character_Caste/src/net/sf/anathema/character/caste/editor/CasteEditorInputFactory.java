package net.sf.anathema.character.caste.editor;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class CasteEditorInputFactory extends AbstractExecutableExtension implements IEditorInputFactory {

  @Override
  public CasteEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      URL imageUrl,
      IDisplayNameProvider nameProvider,
      IModelCollection modelProvider) throws PersistenceException, CoreException {
    ICasteModel casteModel = (ICasteModel) modelProvider.getModel(new ModelIdentifier(characterId, ICasteModel.ID));
    IExperience experience = (IExperience) modelProvider.getModel(new ModelIdentifier(characterId, IExperience.MODEL_ID));
    return new CasteEditorInput(modelFile, imageUrl, nameProvider, casteModel, experience);
  }
}