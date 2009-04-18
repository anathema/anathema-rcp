package charactertype.sidereal.acceptance;

import net.sf.anathema.character.core.model.ModelCache;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import character.acceptance.AcceptanceCasteUtilities;
import character.acceptance.AcceptanceCharacterUtilities;

public class SiderealMinimalAbilityValues_UpdateToCasteTest {

  private IFolder folder;

  @Before
  public void createSiderealFolder() throws Exception {
    folder = AcceptanceCharacterUtilities.createCharacterFolder(
        IAcceptanceConstants.DEFAULT_TEMPLATE,
        "Sidereal"); //$NON-NLS-1$
  }

  @Test
  public void hasCastelessMinimalAblitiesWithoutCaste() throws Exception {
    SiderealAbilityAsserts.assertCastelessMinimalAbilities(folder);
  }

  @Test
  public void hasJourneysMinimalAbilitiesWithJourneysCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Journeys"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertJourneysMinimalAbilities(folder);
  }

  @Test
  public void hasSerenityMinimalAbilitiesWithSerenityCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Serenity"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertSerenityMinimalAbilities(folder);
  }

  @Test
  public void hasBattlesMinimalAbilitiesWithBattlesCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Battles"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertBattlesMinimalAbilities(folder);
  }

  @Test
  public void hasSecretsMinimalAbilitiesWithSecretsCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Secrets"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertSecretsMinimalAbilities(folder);
  }

  @Test
  public void hasEndingsMinimalAbilitiesWithEndingsCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Endings"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertEndingsMinimalAbilities(folder);
  }

  @After
  public void deleteSiderealFolder() throws CoreException {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
    folder = null;
  }
}