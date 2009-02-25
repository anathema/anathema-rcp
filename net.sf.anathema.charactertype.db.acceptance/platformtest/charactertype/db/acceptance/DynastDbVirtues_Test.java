package charactertype.db.acceptance;

import character.acceptance.tests.AbstractVirtues_Test;

@SuppressWarnings("nls")
public class DynastDbVirtues_Test extends AbstractVirtues_Test {

  @Override
  protected String getFolderName() {
    return "DynastDb";
  }

  @Override
  protected String getTemplateId() {
    return IAcceptanceConstants.DYNAST_TEMPLATE_ID;
  }
}