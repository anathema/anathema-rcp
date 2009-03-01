package net.sf.anathema.character.spiritualtraits.points.virtues;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.points.PointwiseCostDto;

import org.junit.Before;
import org.junit.Test;

public class VirtuesCostDtoFactory_Test {

  private PointwiseCostDto dto;

  @Before
  public void createDto() throws Exception {
    final VirtuesCostDtoFactory factory = new VirtuesCostDtoFactory();
    this.dto = factory.create();
  }

  @Test
  public void hasPointCostOfThree() throws Exception {
    assertThat(dto.pointCost, is(3));
  }

  @Test
  public void hasStartValueOfOne() throws Exception {
    assertThat(dto.startValue, is(1));
  }
}