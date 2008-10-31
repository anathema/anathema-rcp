package charactertype.sidereal.acceptance;

import net.sf.anathema.character.acceptance.AcceptanceCasteUtilities;
import net.sf.anathema.character.acceptance.AcceptanceCharacterUtilities;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abilities.integration.AbilitiesInteractionUtilties;

public class SiderealMinimalAbilityValues_InteractionTest {

  private IFolder folder;

  @Before
  public void createSiderealFolder() throws Exception {
    folder = AcceptanceCharacterUtilities.createCharacterFolder(
        ISiderealAcceptanceConstants.DEFAULT_TEMPLATE_ID,
        "Sidereal"); //$NON-NLS-1$
  }

  @Test
  public void hasCastelessMinimalAblitiesWithoutCaste() throws Exception {
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertCastelessMinimalAbilities(folder);
  }

  @Test
  public void hasJourneysMinimalAbilitiesWithJourneysCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Journeys"); //$NON-NLS-1$
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertJourneysMinimalAbilities(folder);
  }

  @Test
  public void journeyCastesHaveSailAsAlternateMinimumToRide() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Journeys"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertAlternateMinimum(folder, "Ride", "Sail", 2); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void hasSerenityMinimalAbilitiesWithSerenityCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Serenity"); //$NON-NLS-1$
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertSerenityMinimalAbilities(folder);
  }

  @Test
  public void serenityCastesHavePerformanceAsAlternateMinimumToCraft() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Serenity"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertAlternateMinimum(folder, "Craft", "Performance", 2); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void hasBattlesMinimalAbilitiesWithBattlesCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Battles"); //$NON-NLS-1$
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertBattlesMinimalAbilities(folder);
  }

  @Test
  public void battleCastesHaveMeleeAsAlternateMinimumToArchery() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Battles"); //$NON-NLS-1$
    SiderealAbilityAsserts.assertAlternateMinimum(folder, "Archery", "Melee", 3); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void hasSecretsMinimalAbilitiesWithSecretsCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Secrets"); //$NON-NLS-1$
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertSecretsMinimalAbilities(folder);
  }

  @Test
  public void hasEndingsMinimalAbilitiesWithEndingsCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Endings"); //$NON-NLS-1$
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertEndingsMinimalAbilities(folder);
  }

  @After
  public void deleteSiderealFolder() throws CoreException {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
    folder = null;
  }

  private void reduceAllValuesByOne() throws Exception {
    for (IDisplayTraitGroup<IInteractiveTrait> group : AbilitiesInteractionUtilties.createDisplayGroups(folder)) {
      for (IInteractiveTrait trait : group.getTraits()) {
        trait.setValue(trait.getValue() - 1);
      }
    }
  }
}