package charactertype.solar.acceptance;

import static org.junit.Assert.*;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.caste.fake.IntegrationCasteModelFactory;
import net.sf.anathema.character.core.character.ICharacterTemplate;

import org.junit.Before;
import org.junit.Test;

import charactertype.solar.acceptance.util.CharacterTemplateObjectMother;

public class SolarCasteTest {
  private ICasteModel casteModel;

  @Before
  public void createCasteModel() {
    ICharacterTemplate template = CharacterTemplateObjectMother.createSolarTemplate();
    casteModel = IntegrationCasteModelFactory.createCasteModel(template);
  }

  @Test
  public void firstIsDawnCaste() throws Exception {
    assertHasCaste(0, "Dawn"); //$NON-NLS-1$
  }

  @Test
  public void secondIsZenithCaste() throws Exception {
    assertHasCaste(1, "Zenith"); //$NON-NLS-1$
  }

  @Test
  public void thirdIshasTwilightCaste() throws Exception {
    assertHasCaste(2, "Twilight"); //$NON-NLS-1$
  }

  @Test
  public void forthIsNightCaste() throws Exception {
    assertHasCaste(3, "Night"); //$NON-NLS-1$
  }

  @Test
  public void fifthIsEclipseCaste() throws Exception {
    assertHasCaste(4, "Eclipse"); //$NON-NLS-1$
  }

  @Test
  public void hasFiveCastes() throws Exception {
    assertEquals(5, casteModel.getOptions().length);
  }

  @Test
  public void hasAbilitiesAsTraitCollection() throws Exception {
    assertEquals("net.sf.anathema.character.abilities.model", casteModel.getTraitModelId()); //$NON-NLS-1$
  }

  private void assertHasCaste(int index, String castePrintName) {
    ICaste caste = casteModel.getOptions()[index];
    assertEquals(castePrintName, caste.getPrintName());
  }
}