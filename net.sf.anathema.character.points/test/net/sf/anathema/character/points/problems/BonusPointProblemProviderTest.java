package net.sf.anathema.character.points.problems;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.repository.problems.IProblem;
import net.sf.anathema.character.core.character.ICharacterId;

import org.easymock.EasyMock;
import org.junit.Test;

public class BonusPointProblemProviderTest {

  @Test
  public void problemAddedForNegativeAvailableBonusPoints() throws Exception {
    assertNumberOfProblemsFound(1, -1);
  }

  @Test
  public void noProblemAddedForNoAvailableBonusPoints() throws Exception {
    assertNumberOfProblemsFound(0, 0);
  }

  @Test
  public void problemAddedForPositiveAvailableBonusPoints() throws Exception {
    assertNumberOfProblemsFound(1, 1);
  }

  private void assertNumberOfProblemsFound(int problemCount, int availableBonusPoints) {
    ICharacterId characterId = EasyMock.createMock(ICharacterId.class);
    BonusPointsProblemProvider problemProvider = new BonusPointsProblemProvider(createBonusPointProvider(
        characterId,
        availableBonusPoints));
    List<IProblem> problemList = new ArrayList<IProblem>();
    problemProvider.addProblemsForCharacter(problemList, characterId);
    assertEquals(problemCount, problemList.size());
  }

  private IBonusPointProvider createBonusPointProvider(ICharacterId characterId, int points) {
    IBonusPointProvider provider = EasyMock.createMock(IBonusPointProvider.class);
    EasyMock.expect(provider.getAvailableBonusPoints(characterId)).andReturn(points).anyTimes();
    EasyMock.replay(provider);
    return provider;
  }
}