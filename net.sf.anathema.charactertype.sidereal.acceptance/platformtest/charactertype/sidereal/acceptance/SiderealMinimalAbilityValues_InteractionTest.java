package charactertype.sidereal.acceptance;

import static character.acceptance.AcceptanceCharacterUtilities.*;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import character.acceptance.AcceptanceCasteUtilities;
import character.acceptance.TraitInteractionUtilties;

@SuppressWarnings("nls")
public class SiderealMinimalAbilityValues_InteractionTest {

  private IFolder folder;

  @Before
  public void createSiderealFolder() throws Exception {
    folder = createCharacterFolder(IAcceptanceConstants.DEFAULT_TEMPLATE, "Sidereal");
  }

  @Test
  public void hasCastelessMinimalAblitiesWithoutCaste() throws Exception {
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertCastelessMinimalAbilities(folder);
  }

  @Test
  public void hasJourneysMinimalAbilitiesWithJourneysCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Journeys");
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertJourneysMinimalAbilities(folder);
  }

  @Test
  public void journeyCastesHaveSailAsAlternateMinimumToRide() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Journeys");
    SiderealAbilityAsserts.assertAlternateMinimum(folder, "Ride", "Sail", 2);
  }

  @Test
  public void hasSerenityMinimalAbilitiesWithSerenityCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Serenity");
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertSerenityMinimalAbilities(folder);
  }

  @Test
  public void serenityCastesHavePerformanceAsAlternateMinimumToCraft() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Serenity");
    SiderealAbilityAsserts.assertAlternateMinimum(folder, "Craft", "Performance", 2);
  }

  @Test
  public void hasBattlesMinimalAbilitiesWithBattlesCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Battles");
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertBattlesMinimalAbilities(folder);
  }

  @Test
  public void battleCastesHaveMeleeAsAlternateMinimumToArchery() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Battles");
    SiderealAbilityAsserts.assertAlternateMinimum(folder, "Archery", "Melee", 3);
  }

  @Test
  public void hasSecretsMinimalAbilitiesWithSecretsCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Secrets");
    reduceAllValuesByOne();
    SiderealAbilityAsserts.assertSecretsMinimalAbilities(folder);
  }

  @Test
  public void hasEndingsMinimalAbilitiesWithEndingsCaste() throws Exception {
    AcceptanceCasteUtilities.setCasteInModel(folder, "Endings");
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
    for (IDisplayTraitGroup<IInteractiveTrait> group : TraitInteractionUtilties.createDisplayGroups(
        folder,
        IAbilitiesPluginConstants.MODEL_ID)) {
      for (IInteractiveTrait trait : group) {
        trait.setValue(trait.getValue() - 1);
      }
    }
  }
}