package charactertype.solar.acceptance;

import static org.junit.Assert.*;
import net.sf.anathema.character.caste.ICaste;
import net.sf.anathema.character.caste.ICasteModel;

import org.junit.Test;

public class SolarCasteTest extends AbstractDefaultSolarTest {

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
    ICasteModel casteModel = (ICasteModel) character.getModel(ICasteModel.ID);
    assertEquals(5, casteModel.getOptions().length);
  }

  @Test
  public void hasAbilitiesAsTraitCollection() throws Exception {
    ICasteModel casteModel = (ICasteModel) character.getModel(ICasteModel.ID);
    assertEquals("net.sf.anathema.character.abilities.model", casteModel.getTraitModelId()); //$NON-NLS-1$
  }

  private void assertHasCaste(int index, String castePrintName) {
    ICasteModel casteModel = (ICasteModel) character.getModel(ICasteModel.ID);
    ICaste caste = casteModel.getOptions()[index];
    assertEquals(castePrintName, caste.getPrintName());
  }
}