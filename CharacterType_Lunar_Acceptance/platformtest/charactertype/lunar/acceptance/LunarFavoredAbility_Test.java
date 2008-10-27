package charactertype.lunar.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.acceptance.AcceptanceCharacterUtilities;
import net.sf.anathema.character.acceptance.AcceptanceTraitUtilities;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveFavorization;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.status.FavoredStatus;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import abilities.integration.AbilitiesInteractionUtilties;

public class LunarFavoredAbility_Test {

  private IFolder folder;

  @Before
  public void createLunarFolder() throws Exception {
    folder = AcceptanceCharacterUtilities.createCharacterFolder(ILunarAcceptanceConstants.PACT_LUNAR_ID, "Lunar"); //$NON-NLS-1$
  }

  @Test
  public void survivalIsInitiallySetToFavored() throws Exception {
    CharacterId characterId = new CharacterId(folder);
    ModelIdentifier abilitiesIdentifier = new ModelIdentifier(characterId, IAbilitiesPluginConstants.MODEL_ID);
    ModelCache modelCache = ModelCache.getInstance();
    ITraitCollectionModel abilitiesModel = (ITraitCollectionModel) modelCache.getModel(abilitiesIdentifier);
    IBasicTrait survival = abilitiesModel.getTrait("Survival"); //$NON-NLS-1$
    assertThat(survival.getStatusManager().getStatus(), is(instanceOf(FavoredStatus.class)));
  }

  @Test
  public void survivalIsRequiredFavored() throws Exception {
    List<IDisplayTraitGroup<IInteractiveTrait>> abilities = AbilitiesInteractionUtilties.createDisplayGroups(folder);
    IInteractiveTrait survival = AcceptanceTraitUtilities.extract(abilities, "Survival"); //$NON-NLS-1$
    IInteractiveFavorization favorization = survival.getFavorization();
    assertTrue(favorization.isFavored());
    favorization.toggleFavored();
    assertTrue(favorization.isFavored());
  }

  @After
  public void deleteLunarFolder() throws CoreException {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
    folder = null;
  }
}