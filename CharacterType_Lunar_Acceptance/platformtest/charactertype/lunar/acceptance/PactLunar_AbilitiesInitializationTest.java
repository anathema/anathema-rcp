package charactertype.lunar.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.character.acceptance.AcceptanceCharacterUtilities;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.status.FavoredStatus;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PactLunar_AbilitiesInitializationTest {

  private IFolder folder;
  private ITraitCollectionModel abilitiesModel;

  @Before
  public void createLunarFolder() throws Exception {
    folder = AcceptanceCharacterUtilities.createCharacterFolder(ILunarAcceptanceConstants.PACT_LUNAR_ID, "Lunar"); //$NON-NLS-1$
    abilitiesModel = CharacterAcceptanceUtilities.getAbilitiesModel(folder);
  }

  @Test
  public void survivalIsFavored() throws Exception {
    IBasicTrait survival = abilitiesModel.getTrait("Survival"); //$NON-NLS-1$
    assertThat(survival.getStatusManager().getStatus(), is(instanceOf(FavoredStatus.class)));
  }

  @Test
  public void survivalHasValue2() throws Exception {
    IBasicTrait survival = abilitiesModel.getTrait("Survival"); //$NON-NLS-1$
    assertThat(survival.getCreationModel().getValue(), is(2));
  }

  @After
  public void deleteLunarFolder() throws CoreException {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
    folder = null;
  }
}