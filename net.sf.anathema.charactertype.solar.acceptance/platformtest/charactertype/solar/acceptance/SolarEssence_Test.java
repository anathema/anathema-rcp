package charactertype.solar.acceptance;

import character.acceptance.tests.AbstractEssenceUserEssence_Test;

public class SolarEssence_Test extends AbstractEssenceUserEssence_Test {

  @Override
  protected String getTemplateId() {
    return IIntegrationConstants.DEFAULT_TEMPLATE;
  }

  @Override
  protected int getBonuspointsForOneIncrement() {
    return 7;
  }

  @Override
  protected int getXpForIncrementFrom2To3() {
    return 16;
  }
}