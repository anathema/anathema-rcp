package net.sf.anathema.character.core.problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.problems.IProblem;
import net.sf.anathema.basics.repository.problems.IProblemProvider;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.core.model.ModelExtensionPoint;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.Path;

public class UneditedModelProblemsProvider extends AbstractExecutableExtension implements IProblemProvider {

  private final ICharacterTemplateProvider templateProvider;

  public UneditedModelProblemsProvider() {
    this(new CharacterTemplateProvider());
  }

  public UneditedModelProblemsProvider(ICharacterTemplateProvider templateProvider) {
    this.templateProvider = templateProvider;
  }

  @Override
  public Collection<IProblem> findProblems(IWorkspaceRoot workspaceRoot) {
    List<IProblem> problems = new ArrayList<IProblem>();
    for (IContainer characterFolder : getCharacterFolders()) {
      for (String modelFileName : new ModelExtensionPoint().getModelFileNames(characterFolder, templateProvider)) {
        IFile file = characterFolder.getFile(new Path(modelFileName));
        if (!file.exists()) {
          problems.add(new UneditedModelProblem(file));
        }
      }
    }
    return problems;
  }

  private List<IFolder> getCharacterFolders() {
    return RepositoryUtilities.getItemFolders(CharacterRepositoryUtilities.getCharacterItemType());
  }
}