package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.groupeditor.TraitCollectionEditorInput;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.junit.Before;
import org.junit.Test;

public class AttributesEditorInputDisplayGroupTest {

  private TraitCollectionEditorInput input;
  private List<IDisplayTraitGroup<IInteractiveTrait>> groups;

  @Before
  public void createEditorInput() {
    TraitGroup[] traitGroups = new TraitGroup[] { new TraitGroup("group1", null, "trait1", "trait2"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        new TraitGroup("group2", null, "trait3", "trait4"), }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    ITraitCollectionContext context = AttributeContextObjectMother.createContext(traitGroups);
    input = new TraitCollectionEditorInput(
        EasyMock.createNiceMock(IFile.class),
        null,
        null,
        context,
        null,
        new AttributesEditorInputConfiguration());
    groups = input.createDisplayGroups();
  }

  @Test
  public void createsCorrectNumberOfDisplayTraitGroups() throws Exception {
    assertEquals(2, groups.size());
  }

  @Test
  public void firstGroupIsCreatedCorrectly() throws Exception {
    Iterator<IInteractiveTrait> iterator1 = groups.get(0).iterator();
    assertEquals("trait1", iterator1.next().getTraitType().getId()); //$NON-NLS-1$
    assertEquals("trait2", iterator1.next().getTraitType().getId()); //$NON-NLS-1$
    assertFalse(iterator1.hasNext());
  }

  @Test
  public void secondGroupIsCreatedCorrectly() throws Exception {
    Iterator<IInteractiveTrait> iterator2 = groups.get(1).iterator();
    assertEquals("trait3", iterator2.next().getTraitType().getId()); //$NON-NLS-1$
    assertEquals("trait4", iterator2.next().getTraitType().getId()); //$NON-NLS-1$
    assertFalse(iterator2.hasNext());
  }
}