package charactertype.db.acceptance;

import character.acceptance.tests.AbstractEssenceUserEssence_Test;

public class DynastDbEssence_Test extends AbstractEssenceUserEssence_Test {

  @Override
  protected int getBonuspointsForOneIncrement() {
    return 10;
  }

  @Override
  protected int getXpForIncrementFrom2To3() {
    return 20;
  }

  @Override
  protected String getTemplateId() {
    return IAcceptanceConstants.DYNAST_TEMPLATE_ID;
  }
}