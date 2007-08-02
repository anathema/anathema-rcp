package net.sf.anathema.character.core.repository.internal;

import static org.junit.Assert.*;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.junit.Before;
import org.junit.Test;

public class CharacterViewElementTest {

  private IFolder folder;
  private CharacterViewElement element;

  @Before
  public void createElement() throws Exception {
    folder = EasyMock.createMock(IFolder.class);
    element = new CharacterViewElement(null, folder, null, null);
  }

  @Test
  public void canBeDeleted() throws Exception {
    assertTrue(element.getAdapter(IPageDelible.class) instanceof IPageDelible);
  }
  
  @Test
  public void canAdaptToResource() throws Exception {
    assertTrue(element.getAdapter(IResource.class) instanceof IFolder);
  }

  @Test
  public void canAdaptToResourceSubclassFolder() throws Exception {
    assertTrue(element.getAdapter(IFolder.class) instanceof IFolder);
  }

  @Test
  public void hasNoAdaptableForObject() throws Exception {
    assertNull(element.getAdapter(Object.class));
  }
}