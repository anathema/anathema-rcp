package charactertype.db.acceptance;

import character.acceptance.tests.AbstractWillpower_Test;

public class DynastDbWillpower_Test extends AbstractWillpower_Test {

  @Override
  protected String getTemplateId() {
    return IAcceptanceConstants.DYNAST_TEMPLATE_ID;
  }
}