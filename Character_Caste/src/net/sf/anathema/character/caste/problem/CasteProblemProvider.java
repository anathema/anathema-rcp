package net.sf.anathema.character.caste.problem;

import java.util.List;

import net.sf.anathema.basics.repository.problems.IProblem;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.problem.AbstractCharacterProblemProvider;
import net.sf.anathema.character.core.repository.ModelResourceHandler;

import org.eclipse.core.resources.IResource;

public class CasteProblemProvider extends AbstractCharacterProblemProvider {

  @Override
  protected void addProblemsForCharacter(List<IProblem> problems, ICharacterId characterId) {
    IResource resource = new ModelResourceHandler().getResource(new ModelIdentifier(characterId, ICasteModel.ID));
    if (!resource.exists()) {
      return;
    }
    characterId.getAdapter(IResource.class);
  }
}