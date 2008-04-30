package charactertype.lunar.acceptance;

import static org.junit.Assert.assertTrue;

import java.util.List;

import net.sf.anathema.character.acceptance.AcceptanceCharacterUtilities;
import net.sf.anathema.character.acceptance.AcceptanceTraitUtilities;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveFavorization;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

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