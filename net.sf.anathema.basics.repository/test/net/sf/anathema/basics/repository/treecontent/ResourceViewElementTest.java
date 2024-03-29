package net.sf.anathema.basics.repository.treecontent;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.junit.Before;
import org.junit.Test;

public class ResourceViewElementTest {

  private ResourceViewElement element;
  private IFile file;

  @Before
  public void createViewElement() {
    this.file = EasyMock.createMock(IFile.class);
    this.element = new ResourceViewElement(file, null, null, null);
  }

  @Test
  public void remembersEditFile() {
    assertEquals(file, element.getEditFile());
  }

  @Test
  public void doesNotKnowAnyChildren() {
    assertEquals(0, element.getChildren().length);
  }

  @Test
  public void hasNoChildren() {
    assertFalse(element.hasChildren());
  }

  @Test
  public void adaptsToIResourceAsFile() throws Exception {
    assertEquals(file, element.getAdapter(IResource.class));
  }

  @Test
  public void canBeDeleted() throws Exception {
    assertTrue(element.getAdapter(IPageDelible.class) instanceof IPageDelible);
  }

  @Test
  public void providesEditorInputs() throws Exception {
    assertTrue(element.getAdapter(IEditorInputProvider.class) instanceof IEditorInputProvider);
  }
}