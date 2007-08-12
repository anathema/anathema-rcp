package net.sf.anathema.character.core.repository.internal;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.ui.IEditorInputProvider;
import net.sf.anathema.basics.repository.treecontent.deletion.IPageDelible;

import org.easymock.EasyMock;
import org.eclipse.core.resources.IFolder;
import org.junit.Before;
import org.junit.Test;

public class CharacterModelViewElementTest {

  private CharacterModelViewElement element;
  private IFolder folder;
  private IModelDisplayConfiguration configuration;

  @Before
  public void createElement() {
    this.folder = EasyMock.createMock(IFolder.class);
    this.configuration = EasyMock.createMock(IModelDisplayConfiguration.class);
    this.element = new CharacterModelViewElement(null, folder, configuration);
  }

  @Test
  public void doesNotAdaptToDelible() {
    assertNull(element.getAdapter(IPageDelible.class));
  }

  @Test
  public void providesEditorInput() {
    assertTrue(element.getAdapter(IEditorInputProvider.class) instanceof IEditorInputProvider);
  }

  @Test
  public void doesNotEqualObject() throws Exception {
    assertFalse(element.equals(new Object()));
  }

  @Test
  public void doesNotEqualOtherCharacterModelElementWithoutFolder() throws Exception {
    assertFalse(element.equals(new CharacterModelViewElement(null, null, null)));
  }

  @Test
  public void equalsSelf() throws Exception {
    assertEquals(element, element);
  }

  @Test
  public void doesNotEqualOtherElementWithSameFolderOnly() throws Exception {
    assertFalse(element.equals(new CharacterModelViewElement(null, folder, null)));
  }

  @Test
  public void doesNotEqualOtherElementWithSameConfigurationOnly() throws Exception {
    assertFalse(element.equals(new CharacterModelViewElement(null, null, configuration)));
  }

  @Test
  public void equalsOtherElementWithSameFolderAndConfiguration() throws Exception {
    assertEquals(element, new CharacterModelViewElement(null, folder, configuration));
  }

}