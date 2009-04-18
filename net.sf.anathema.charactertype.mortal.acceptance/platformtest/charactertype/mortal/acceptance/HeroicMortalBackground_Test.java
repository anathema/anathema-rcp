package charactertype.mortal.acceptance;

import character.acceptance.tests.AbstractBackground_Test;

public class HeroicMortalBackground_Test extends AbstractBackground_Test {

  @Override
  protected String getTemplateId() {
    return IAcceptanceConstants.HEROIC_TEMPLATE_ID;
  }
}