package net.sf.anathema.character.points.problems;

import net.sf.anathema.basics.repository.problems.IProblem;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.repository.ModelDisplayNameProvider;
import net.sf.anathema.character.core.resource.CharacterDisplayNameProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IWorkbenchPage;

public class BonusPointProblem implements IProblem {

  private final ICharacterId characterId;
  private final int availableBonusPoints;

  public BonusPointProblem(ICharacterId characterId, int availableBonusPoints) {
    this.characterId = characterId;
    this.availableBonusPoints = availableBonusPoints;
  }

  @Override
  public String getDescription() {
    if (availableBonusPoints > 0) {
      return NLS.bind(Messages.BonusPointProblem_Underspent, getCharacterName());
    }
    return NLS.bind(Messages.BonusPointProblem_Overspent, getCharacterName());
  }

  private String getCharacterName() {
    return new CharacterDisplayNameProvider(getContainer()).getDisplayName();
  }

  private IContainer getContainer() {
    return (IContainer) characterId.getAdapter(IContainer.class);
  }

  @Override
  public String getPath() {
    return new ModelDisplayNameProvider(Messages.BonusPointProblem_ModelName, new CharacterDisplayNameProvider(getContainer())).getDisplayName();
  }

  @Override
  public void showSource(IWorkbenchPage page) throws CoreException {
    // nothing to do
  }
}