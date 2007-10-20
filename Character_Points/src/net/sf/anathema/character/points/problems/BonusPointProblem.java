package net.sf.anathema.character.points.problems;

import net.sf.anathema.basics.repository.problems.IProblem;
import net.sf.anathema.character.core.character.ICharacterId;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbenchPage;

public class BonusPointProblem implements IProblem {

  public BonusPointProblem(ICharacterId characterId, int availableBonusPoints) {
    // TODO Auto-generated constructor stub
  }

  @Override
  public String getDescription() {
    return "BonusPoints not correct";
  }

  @Override
  public String getPath() {
    return "Pfff";
  }

  @Override
  public void showSource(IWorkbenchPage page) throws CoreException {
    // TODO Auto-generated method stub
    
  }
}