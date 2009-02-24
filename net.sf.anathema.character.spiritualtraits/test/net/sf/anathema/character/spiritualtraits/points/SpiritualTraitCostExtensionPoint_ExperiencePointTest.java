package net.sf.anathema.character.spiritualtraits.points;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.spiritualtraits.points.essence.IEssenceCostMap;

import org.junit.Test;

@SuppressWarnings("nls")
public class SpiritualTraitCostExtensionPoint_ExperiencePointTest extends AbstractSpiritualTraitCostExtensionPoint_Test {

  public SpiritualTraitCostExtensionPoint_ExperiencePointTest() {
    super("experience");
  }

  @Test
  public void returnsDefinedCostForDefinedType() throws Exception {
    IEssenceCostMap costExtensionPoint = createCostExtensionPoint();
    assertThat(costExtensionPoint.getEssenceExperienceCost(DEFINED_TYPE), is(DEFINED_COST));
  }

  @Test
  public void returnsZeroCostForUndefinedType() throws Exception {
    IEssenceCostMap costExtensionPoint = createCostExtensionPoint();
    assertThat(costExtensionPoint.getEssenceExperienceCost("undefinedType"), is(0));
  }
}