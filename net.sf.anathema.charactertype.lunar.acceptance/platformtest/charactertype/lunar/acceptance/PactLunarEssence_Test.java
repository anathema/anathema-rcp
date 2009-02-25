package charactertype.lunar.acceptance;

import character.acceptance.tests.AbstractEssenceUserEssence_Test;

public class PactLunarEssence_Test extends AbstractEssenceUserEssence_Test {

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
    return ILunarAcceptanceConstants.PACT_LUNAR_ID;
  }
}