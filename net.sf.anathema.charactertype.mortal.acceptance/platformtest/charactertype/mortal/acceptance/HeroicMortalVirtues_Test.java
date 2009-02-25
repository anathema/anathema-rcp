package charactertype.mortal.acceptance;

import character.acceptance.tests.AbstractVirtues_Test;

@SuppressWarnings("nls")
public class HeroicMortalVirtues_Test extends AbstractVirtues_Test {

  @Override
  protected String getFolderName() {
    return "HeroicMortal";
  }

  @Override
  protected String getTemplateId() {
    return IAcceptanceConstants.HEROIC_TEMPLATE_ID;
  }
}