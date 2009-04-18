package charactertype.db.acceptance;

import character.acceptance.tests.AbstractBackground_Test;

public class DynastDbBackground_Test extends AbstractBackground_Test {

  @Override
  protected String getTemplateId() {
    return IAcceptanceConstants.DYNAST_TEMPLATE_ID;
  }
}