package net.sf.anathema.character.experience.points;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.character.experience.IExperiencePoints;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class ExperiencePointsEditorInputFactory extends UnconfiguredExecutableExtension implements IEditorInputFactory {

  @Override
  public ExperiencePointsEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      URL imageUrl,
      IDisplayNameProvider nameProvider,
      IModelCollection modelProvider) throws PersistenceException, CoreException {
    ModelIdentifier identifier = new ModelIdentifier(characterId, IExperiencePoints.MODEL_ID);
    IExperiencePoints model = (IExperiencePoints) modelProvider.getModel(identifier);
    return new ExperiencePointsEditorInput(modelFile, imageUrl, nameProvider, model);
  }
}