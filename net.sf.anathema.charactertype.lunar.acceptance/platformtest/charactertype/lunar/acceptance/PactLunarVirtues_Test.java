package charactertype.lunar.acceptance;

import character.acceptance.tests.AbstractVirtues_Test;

@SuppressWarnings("nls")
public class PactLunarVirtues_Test extends AbstractVirtues_Test {

  @Override
  protected String getFolderName() {
    return "PactLunar";
  }

  @Override
  protected String getTemplateId() {
    return ILunarAcceptanceConstants.PACT_LUNAR_ID;
  }
}