package net.sf.anathema.character.spiritualtraits.points.virtues;

import net.sf.anathema.character.trait.points.PointwiseCostDto;

public class VirtuesCostDtoFactory {

  public PointwiseCostDto create() {
    final PointwiseCostDto costDto = new PointwiseCostDto();
    costDto.pointCost = 3;
    costDto.startValue = 1;
    return costDto;
  }
}