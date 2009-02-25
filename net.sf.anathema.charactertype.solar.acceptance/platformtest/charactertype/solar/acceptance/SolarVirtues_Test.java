package charactertype.solar.acceptance;

import character.acceptance.tests.AbstractVirtues_Test;

@SuppressWarnings("nls")
public class SolarVirtues_Test extends AbstractVirtues_Test {

  @Override
  protected String getFolderName() {
    return "DefaultSolar";
  }

  @Override
  protected String getTemplateId() {
    return IIntegrationConstants.DEFAULT_TEMPLATE;
  }
}