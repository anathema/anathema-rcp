package net.sf.anathema.character.points.problems;

import java.util.List;

import net.sf.anathema.basics.repository.problems.IProblem;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.problem.AbstractCharacterProblemProvider;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.points.configuration.internal.PointConfigurationExtensionPoint;

public class BonusPointsProblemProvider extends AbstractCharacterProblemProvider {

  private final IBonusPointProvider bonusPointProvider;

  public BonusPointsProblemProvider() {
    this(new BonusPointsProvider(
        ModelCache.getInstance(),
        new CharacterTemplateProvider(),
        new BonusPointExtensionPoint(),
        new PointConfigurationExtensionPoint()));
  }

  public BonusPointsProblemProvider(IBonusPointProvider bonusPointProvider) {
    this.bonusPointProvider = bonusPointProvider;
  }

  @Override
  protected void addProblemsForCharacter(List<IProblem> problems, ICharacterId characterId) {
    int availableBonusPoints = bonusPointProvider.getAvailableBonusPoints(characterId);
    if (availableBonusPoints != 0) {
      problems.add(new BonusPointProblem(characterId, availableBonusPoints));
    }
  }
}