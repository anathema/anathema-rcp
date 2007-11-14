package net.sf.anathema.character.core.problem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.problems.IProblem;
import net.sf.anathema.basics.repository.problems.IProblemProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.internal.CharacterId;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;

public abstract class AbstractCharacterProblemProvider extends AbstractExecutableExtension implements IProblemProvider {

  @Override
  public final Collection<IProblem> findProblems(IContainer workspaceRoot) {
    List<IProblem> problems = new ArrayList<IProblem>();
    for (IContainer characterFolder : getCharacterFolders()) {
      addProblemsForCharacter(problems, new CharacterId(characterFolder));
    }
    return problems;
  }

  protected abstract void addProblemsForCharacter(List<IProblem> problems, ICharacterId characterId);

  private List<IFolder> getCharacterFolders() {
    return RepositoryUtilities.getItemFolders(CharacterRepositoryUtilities.getCharacterItemType());
  }
}