package charactertype.sidereal.acceptance;

import character.acceptance.tests.AbstractVirtues_Test;

@SuppressWarnings("nls")
public class SiderealVirtues_Test extends AbstractVirtues_Test {

  @Override
  protected String getFolderName() {
    return "DefaultSidereal";
  }

  @Override
  protected String getTemplateId() {
    return ISiderealAcceptanceConstants.DEFAULT_TEMPLATE_ID;
  }
}