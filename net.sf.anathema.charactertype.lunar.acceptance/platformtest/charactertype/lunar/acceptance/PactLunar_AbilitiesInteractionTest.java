package charactertype.lunar.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveFavorization;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import character.acceptance.AcceptanceCharacterUtilities;
import character.acceptance.AcceptanceTraitUtilities;
import character.acceptance.TraitInteractionUtilties;

public class PactLunar_AbilitiesInteractionTest {

  private IFolder folder;
  private List<IDisplayTraitGroup<IInteractiveTrait>> interactiveAbilities;
  private IInteractiveTrait interactiveSurvival;
  private ITraitCollectionModel abilities;
  private IBasicTrait survival;

  @Before
  public void createLunarFolder() throws Exception {
    folder = AcceptanceCharacterUtilities.createCharacterFolder(IAcceptanceConstants.PACT_LUNAR_ID, "Lunar"); //$NON-NLS-1$
    interactiveAbilities = TraitInteractionUtilties.createDisplayGroups(folder, IAbilitiesPluginConstants.MODEL_ID);
    abilities = (ITraitCollectionModel) ModelCache.getInstance().getModel(
        new ModelIdentifier(new CharacterId(folder), IAbilitiesPluginConstants.MODEL_ID));
    interactiveSurvival = AcceptanceTraitUtilities.extract(interactiveAbilities, "Survival"); //$NON-NLS-1$
    survival = abilities.getTrait("Survival"); //$NON-NLS-1$
  }

  @Test
  public void survivalIsRequiredFavored() throws Exception {
    IInteractiveFavorization favorization = interactiveSurvival.getFavorization();
    assertTrue(favorization.isFavored());
    favorization.toggleFavored();
    assertTrue(favorization.isFavored());
  }

  @Test
  public void cannotSetSurvivalToOneOnCreation() throws Exception {
    interactiveSurvival.setValue(1);
    assertThat(interactiveSurvival.getValue(), is(2));
    assertThat(survival.getCreationModel().getValue(), is(2));
    assertThat(survival.getExperiencedModel().getValue(), is(-1));
  }

  @Test
  public void setsSurvivalToThreeOnCreation() throws Exception {
    interactiveSurvival.setValue(3);
    assertThat(interactiveSurvival.getValue(), is(3));
    assertThat(survival.getCreationModel().getValue(), is(3));
    assertThat(survival.getExperiencedModel().getValue(), is(-1));
  }

  @After
  public void deleteLunarFolder() throws CoreException {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
    folder = null;
  }
}