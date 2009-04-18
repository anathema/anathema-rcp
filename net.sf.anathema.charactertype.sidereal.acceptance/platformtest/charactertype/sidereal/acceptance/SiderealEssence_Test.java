package charactertype.sidereal.acceptance;

import character.acceptance.tests.AbstractEssenceUserEssence_Test;

public class SiderealEssence_Test extends AbstractEssenceUserEssence_Test {

  @Override
  protected int getBonuspointsForOneIncrement() {
    return 10;
  }

  @Override
  protected int getXpForIncrementFrom2To3() {
    return 18;
  }

  @Override
  protected String getTemplateId() {
    return IAcceptanceConstants.DEFAULT_TEMPLATE;
  }
}