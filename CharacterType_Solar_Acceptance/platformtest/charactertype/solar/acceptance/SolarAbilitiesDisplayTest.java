package charactertype.solar.acceptance;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.core.character.CharacterId;
import net.sf.anathema.character.core.create.CharacterFactory;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.trait.display.IDisplayFavorization;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.status.DefaultStatus;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import abilities.integration.AbilitiesDisplayUtilties;

public class SolarAbilitiesDisplayTest {

  private static IFolder folder;
  private static List<IDisplayTraitGroup<IDisplayTrait>> groups;

  @BeforeClass
  public static void createSolarFolder() {
    new CharacterFactory().createNewCharacter(IIntegrationConstants.DEFAULT_TEMPLATE, "Solar"); //$NON-NLS-1$
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    folder = project.getFolder("Solar"); //$NON-NLS-1$
    CharacterId characterId = new CharacterId(folder);
    groups = AbilitiesDisplayUtilties.createDisplayAttributeGroups(characterId);
  }

  @Test
  public void firstGroupIsDawn() throws Exception {
    IDisplayTraitGroup<IDisplayTrait> group = groups.get(0);
    assertEquals("Dawn", group.getId()); //$NON-NLS-1$
    Iterator<IDisplayTrait> groupTraits = group.getTraits().iterator();
    assertCreationTrait(groupTraits.next(), "Archery"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "MartialArts"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Melee"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Thrown"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "War"); //$NON-NLS-1$
    assertFalse(groupTraits.hasNext());
  }

  @Test
  public void secondGroupIsZenith() throws Exception {
    IDisplayTraitGroup<IDisplayTrait> group = groups.get(1);
    assertEquals("Zenith", group.getId()); //$NON-NLS-1$
    Iterator<IDisplayTrait> groupTraits = group.getTraits().iterator();
    assertCreationTrait(groupTraits.next(), "Integrity"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Performance"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Presence"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Resistance"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Survival"); //$NON-NLS-1$
    assertFalse(groupTraits.hasNext());
  }

  @Test
  public void thirdGroupIsTwilight() throws Exception {
    IDisplayTraitGroup<IDisplayTrait> group = groups.get(2);
    assertEquals("Twilight", group.getId()); //$NON-NLS-1$
    Iterator<IDisplayTrait> groupTraits = group.getTraits().iterator();
    assertCreationTrait(groupTraits.next(), "Craft"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Investigation"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Lore"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Medicine"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Occult"); //$NON-NLS-1$
    assertFalse(groupTraits.hasNext());
  }

  @Test
  public void forthGroupIsNight() throws Exception {
    IDisplayTraitGroup<IDisplayTrait> group = groups.get(3);
    assertEquals("Night", group.getId()); //$NON-NLS-1$
    Iterator<IDisplayTrait> groupTraits = group.getTraits().iterator();
    assertCreationTrait(groupTraits.next(), "Athletics"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Awareness"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Dodge"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Larceny"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Stealth"); //$NON-NLS-1$
    assertFalse(groupTraits.hasNext());
  }

  @Test
  public void fifthGroupIsEclipse() throws Exception {
    IDisplayTraitGroup<IDisplayTrait> group = groups.get(4);
    assertEquals("Eclipse", group.getId()); //$NON-NLS-1$
    Iterator<IDisplayTrait> groupTraits = group.getTraits().iterator();
    assertCreationTrait(groupTraits.next(), "Bureaucracy"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Linguistics"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Ride"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Sail"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Socialize"); //$NON-NLS-1$
    assertFalse(groupTraits.hasNext());
  }

  @Test
  public void hasFiveDisplayGroups() throws Exception {
    assertEquals(5, groups.size());
  }

  private void assertCreationTrait(IDisplayTrait displayTrait, String traitId) {
    assertEquals(traitId, displayTrait.getTraitType().getId());
    assertEquals(5, displayTrait.getMaximalValue());
    assertEquals(0, displayTrait.getValue());
    IDisplayFavorization favorization = displayTrait.getFavorization();
    assertFalse(favorization.isFavorable());
    assertTrue(favorization.getStatusModel().getStatus() instanceof DefaultStatus);
  }

  @AfterClass
  public static void deleteSolarFolder() throws CoreException {
    folder.delete(true, null);
    folder = null;
    groups = null;
  }
}