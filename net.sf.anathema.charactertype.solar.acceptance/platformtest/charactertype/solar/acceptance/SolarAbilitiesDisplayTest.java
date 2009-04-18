package charactertype.solar.acceptance;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.character.abilities.util.IAbilitiesPluginConstants;
import net.sf.anathema.character.core.create.CharacterFactory;
import net.sf.anathema.character.core.create.CharacterRepositoryUtilities;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.trait.display.IDisplayFavorization;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.status.DefaultStatus;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import character.acceptance.TraitInteractionUtilties;

public class SolarAbilitiesDisplayTest {

  private static IFolder folder;
  private static List<IDisplayTraitGroup<IInteractiveTrait>> groups;

  @BeforeClass
  public static void createSolarFolder() throws Exception {
    new CharacterFactory().createNewCharacter(IAcceptanceConstants.DEFAULT_TEMPLATE, "Solar"); //$NON-NLS-1$
    IProject project = RepositoryUtilities.getProject(CharacterRepositoryUtilities.getCharacterItemType());
    folder = project.getFolder("Solar"); //$NON-NLS-1$
    groups = TraitInteractionUtilties.createDisplayGroups(folder, IAbilitiesPluginConstants.MODEL_ID);
  }

  @Test
  public void firstGroupIsDawn() throws Exception {
    IDisplayTraitGroup<IInteractiveTrait> group = groups.get(0);
    assertEquals("Dawn", group.getId()); //$NON-NLS-1$
    Iterator<IInteractiveTrait> groupTraits = group.iterator();
    assertCreationTrait(groupTraits.next(), "Archery"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "MartialArts"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Melee"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Thrown"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "War"); //$NON-NLS-1$
    assertFalse(groupTraits.hasNext());
  }

  @Test
  public void secondGroupIsZenith() throws Exception {
    IDisplayTraitGroup<IInteractiveTrait> group = groups.get(1);
    assertEquals("Zenith", group.getId()); //$NON-NLS-1$
    Iterator<IInteractiveTrait> groupTraits = group.iterator();
    assertCreationTrait(groupTraits.next(), "Integrity"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Performance"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Presence"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Resistance"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Survival"); //$NON-NLS-1$
    assertFalse(groupTraits.hasNext());
  }

  @Test
  public void thirdGroupIsTwilight() throws Exception {
    IDisplayTraitGroup<IInteractiveTrait> group = groups.get(2);
    assertEquals("Twilight", group.getId()); //$NON-NLS-1$
    Iterator<IInteractiveTrait> groupTraits = group.iterator();
    assertCreationTrait(groupTraits.next(), "Craft"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Investigation"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Lore"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Medicine"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Occult"); //$NON-NLS-1$
    assertFalse(groupTraits.hasNext());
  }

  @Test
  public void forthGroupIsNight() throws Exception {
    IDisplayTraitGroup<IInteractiveTrait> group = groups.get(3);
    assertEquals("Night", group.getId()); //$NON-NLS-1$
    Iterator<IInteractiveTrait> groupTraits = group.iterator();
    assertCreationTrait(groupTraits.next(), "Athletics"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Awareness"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Dodge"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Larceny"); //$NON-NLS-1$
    assertCreationTrait(groupTraits.next(), "Stealth"); //$NON-NLS-1$
    assertFalse(groupTraits.hasNext());
  }

  @Test
  public void fifthGroupIsEclipse() throws Exception {
    IDisplayTraitGroup<IInteractiveTrait> group = groups.get(4);
    assertEquals("Eclipse", group.getId()); //$NON-NLS-1$
    Iterator<IInteractiveTrait> groupTraits = group.iterator();
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
    assertEquals(10, displayTrait.getMaximalValue());
    assertEquals(0, displayTrait.getValue());
    IDisplayFavorization favorization = displayTrait.getFavorization();
    assertTrue(favorization.isFavorable());
    assertTrue(favorization.getStatus() instanceof DefaultStatus);
  }

  @AfterClass
  public static void deleteSolarFolder() throws CoreException {
    ModelCache.getInstance().clear();
    folder.delete(true, null);
    folder = null;
    groups = null;
  }
}