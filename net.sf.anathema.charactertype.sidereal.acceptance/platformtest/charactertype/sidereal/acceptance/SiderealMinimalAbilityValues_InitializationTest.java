package charactertype.sidereal.acceptance;

import net.sf.anathema.character.core.model.ModelCache;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import character.acceptance.AcceptanceCasteUtilities;
import character.acceptance.AcceptanceCharacterUtilities;

public class SiderealMinimalAbilityValues_InitializationTest {

  private IFolder folder;

  @Before
  public void createSiderealFolder() throws Exception {
    folder = AcceptanceCharacterUtilities.createCharacterFolder(
        ISiderealAcceptanceConstants.DEFAULT_TEMPLATE_ID,
        "Sidereal"); //$NON-NLS-1$
  }

  @Test
  public void hasJourneysMinimalAbilitiesAfterSettingJourneysCaste() throws Exception {
    AcceptanceCasteUtilities.initPersistentCaste(folder, "Journeys"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertJourneysMinimalAbilities(folder);
  }

  @Test
  public void hasSerenityMinimalAbilitiesAfterSettingSerenityCaste() throws Exception {
    AcceptanceCasteUtilities.initPersistentCaste(folder, "Serenity"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertSerenityMinimalAbilities(folder);
  }

  @Test
  public void hasBattlesMinimalAbilitiesAfterSettingBattlesCaste() throws Exception {
    AcceptanceCasteUtilities.initPersistentCaste(folder, "Battles"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertBattlesMinimalAbilities(folder);
  }

  @Test
  public void hasSecretsMinimalAbilitiesAfterSettingSecretsCaste() throws Exception {
    AcceptanceCasteUtilities.initPersistentCaste(folder, "Secrets"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertSecretsMinimalAbilities(folder);
  }

  @Test
  public void hasEndingsMinimalAbilitiesAfterSettingEndingsCaste() throws Exception {
    AcceptanceCasteUtilities.initPersistentCaste(folder, "Endings"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertEndingsMinimalAbilities(folder);
  }

  @After
  public void deleteSiderealFolder() throws CoreException {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
    folder = null;
  }
}