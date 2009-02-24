package net.sf.anathema.character.spiritualtraits.points.essence;

import net.sf.anathema.character.trait.points.PointwiseCostDto;

public class EssenceCostDtoFactory {

  private final IEssenceCostMap costMap;

  public EssenceCostDtoFactory(IEssenceCostMap costMap) {
    this.costMap = costMap;
  }

  public PointwiseCostDto createCost(String characterType) {
    PointwiseCostDto costDto = new PointwiseCostDto();
    costDto.pointCost = costMap.getEssenceBonuspointCost(characterType);
    costDto.startValue = 2;
    return costDto;
  }
}