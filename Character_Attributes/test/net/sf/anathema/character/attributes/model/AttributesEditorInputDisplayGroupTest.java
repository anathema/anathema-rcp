package net.sf.anathema.character.attributes.model;

import static org.junit.Assert.*;

import java.util.Iterator;

import net.sf.anathema.character.trait.IInteractiveTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.junit.Before;
import org.junit.Test;

public class AttributesEditorInputDisplayGroupTest {

  private AttributesEditorInput input;
  private IDisplayTraitGroup[] groups;

  @Before
  public void createEditorInput() {
    TraitGroup[] traitGroups = new TraitGroup[] { new TraitGroup("group1", "trait1", "trait2"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        new TraitGroup("group2", "trait3", "trait4"), }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    ITraitCollectionContext context = AttributeContextObjectMother.createContext(traitGroups);
    this.input = new AttributesEditorInput(EasyMock.createNiceMock(IFile.class), null, null, context, null);
    groups = input.createDisplayGroups();
  }

  @Test
  public void createsCorrectNumberOfDisplayTraitGroups() throws Exception {
    assertEquals(2, groups.length);
  }

  @Test
  public void firstGroupIsCreatedCorrectly() throws Exception {
    Iterator<IInteractiveTrait> iterator1 = groups[0].getTraits().iterator();
    assertEquals("trait1", iterator1.next().getTraitType().getId()); //$NON-NLS-1$
    assertEquals("trait2", iterator1.next().getTraitType().getId()); //$NON-NLS-1$
    assertFalse(iterator1.hasNext());
  }

  @Test
  public void secondGroupIsCreatedCorrectly() throws Exception {
    Iterator<IInteractiveTrait> iterator2 = groups[1].getTraits().iterator();
    assertEquals("trait3", iterator2.next().getTraitType().getId()); //$NON-NLS-1$
    assertEquals("trait4", iterator2.next().getTraitType().getId()); //$NON-NLS-1$
    assertFalse(iterator2.hasNext());
  }
}