package charactertype.mortal.acceptance;

import character.acceptance.tests.AbstractWillpower_Test;

public class HeroicMortalWillpower_Test extends AbstractWillpower_Test {

  @Override
  protected String getTemplateId() {
    return IAcceptanceConstants.HEROIC_TEMPLATE_ID;
  }
}