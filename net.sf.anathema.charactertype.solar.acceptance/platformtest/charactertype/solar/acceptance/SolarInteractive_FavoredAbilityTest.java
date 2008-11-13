package charactertype.solar.acceptance;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.create.CharacterFactory;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import abilities.integration.AbilitiesInteractionUtilties;

public class SolarInteractive_FavoredAbilityTest {

  private static IFolder folder;
  private static IInteractiveTrait favoredAbility;

  @BeforeClass
  public static void createSolarFolderWithFirstAttributeFavored() throws Exception {
    new CharacterFactory().createNewCharacter(IIntegrationConstants.DEFAULT_TEMPLATE, "Solar"); //$NON-NLS-1$
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    folder = project.getFolder("Solar"); //$NON-NLS-1$
    List<IDisplayTraitGroup<IInteractiveTrait>> groups = AbilitiesInteractionUtilties.createDisplayGroups(folder);
    favoredAbility = groups.get(0).getTraits().iterator().next();
    ModelIdentifier abilitiesIdentifier = new ModelIdentifier(new CharacterId(folder), IAbilitiesPluginConstants.MODEL_ID);
    ITraitCollectionModel abilities = (ITraitCollectionModel) ModelCache.getInstance().getModel(abilitiesIdentifier);
    abilities.getTrait(favoredAbility.getTraitType().getId()).getStatusManager().toggleStatus();
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

  @AfterClass
  public static void deleteSolarFolder() throws CoreException {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
    folder = null;
    favoredAbility = null;
  }
}