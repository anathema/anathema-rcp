package net.sf.anathema.character.spiritualtraits.points.essence;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.trait.points.PointwiseCostDto;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class EssenceCostDtoFactory_Test {

  private static final int ESSENCE_COST = 4;
  private static final String CHARACTER_TYPE = "type";
  private PointwiseCostDto costDto;

  @Before
  public void createCostDtoForCharacterType() throws Exception {
    IEssenceCostMap costMap = createCostMap(ESSENCE_COST);
    EssenceCostDtoFactory factory = new EssenceCostDtoFactory(costMap);
    costDto = factory.createCost(CHARACTER_TYPE);
  }

  @Test
  public void createsDtoWithStartValue2() throws Exception {
    assertThat(costDto.startValue, is(2));
  }

  @Test
  public void createsDtoFromMap() throws Exception {
    assertThat(costDto.pointCost, is(4));
  }

  private IEssenceCostMap createCostMap(int value) {
    IEssenceCostMap costMap = createMock(IEssenceCostMap.class);
    expect(costMap.getEssenceBonuspointCost(CHARACTER_TYPE)).andStubReturn(value);
    replay(costMap);
    return costMap;
  }
}