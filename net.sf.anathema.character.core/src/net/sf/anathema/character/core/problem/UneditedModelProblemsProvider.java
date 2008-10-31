package net.sf.anathema.character.core.problem;

import java.util.List;

import net.sf.anathema.basics.repository.problems.IProblem;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IResource;

public class UneditedModelProblemsProvider extends AbstractCharacterProblemProvider {

  private final ICharacterTemplateProvider templateProvider;

  public UneditedModelProblemsProvider() {
    this(new CharacterTemplateProvider());
  }

  public UneditedModelProblemsProvider(ICharacterTemplateProvider templateProvider) {
    this.templateProvider = templateProvider;
  }

  @Override
  protected void addProblemsForCharacter(List<IProblem> problems, ICharacterId characterId) {
    for (String modelFileName : new ModelExtensionPoint().getModelFileNames(characterId, templateProvider)) {
      IResource file = (IResource) characterId.getContents(modelFileName).getAdapter(IResource.class);
      if (file != null &&!file.exists()) {
        problems.add(new UneditedModelProblem(file));
      }
    }
  }
}