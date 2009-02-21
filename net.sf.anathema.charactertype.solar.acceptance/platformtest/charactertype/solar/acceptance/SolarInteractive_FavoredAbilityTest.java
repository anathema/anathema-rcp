package charactertype.solar.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.acceptance.AcceptanceCharacter;
import net.sf.anathema.character.acceptance.InteractionTraitList;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SolarInteractive_FavoredAbilityTest {

  private static IInteractiveTrait favoredAbility;
  private AcceptanceCharacter character;

  @Before
  public void createSolarFolderWithFirstAttributeFavored() throws Exception {
    this.character = AcceptanceCharacter.FromFolderNameAndTemplateId("Solar", IIntegrationConstants.DEFAULT_TEMPLATE); //$NON-NLS-1$
    ITraitCollectionModel abilities = (ITraitCollectionModel) character.getModel(IAbilitiesPluginConstants.MODEL_ID);
    InteractionTraitList groups = character.createTraitInteraction(IAbilitiesPluginConstants.MODEL_ID);
    favoredAbility = groups.setTraitFavoredAndReturn(abilities, 0, 0);
    assertThat(favoredAbility.getFavorization().isFavored(), is(true));
  }

  @Test
  public void isFavoredsetsAbilityValueToOneOnFavorization() throws Exception {
    assertTrue(favoredAbility.getFavorization().isFavored());
  }

  @Test
  public void hasValueOneForFavoredAbility() throws Exception {
    assertEquals(1, favoredAbility.getValue());
  }

  @Test
  public void leavesValueOfAbiltyWithValueTwoUnchangedOnToggleToFavored() throws Exception {
    favoredAbility.setValue(2);
    favoredAbility.getFavorization().toggleFavored();
    favoredAbility.getFavorization().toggleFavored();
    assertEquals(2, favoredAbility.getValue());
  }

  @Test
  public void doesNotReduceValueOfFavoredAbilityToZero() throws Exception {
    favoredAbility.setValue(0);
    assertEquals(1, favoredAbility.getValue());
  }

  @After
  public void deleteSolarFolder() throws Exception {
    character.clear();
    favoredAbility = null;
  }
}