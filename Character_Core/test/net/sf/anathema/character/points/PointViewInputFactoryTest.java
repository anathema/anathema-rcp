package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.basics.item.editor.IEditorInputProvider;
import net.sf.anathema.character.core.model.IModelIdentifier;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class PointViewInputFactoryTest {

  private PointViewInputFactory viewInputFactory;


  private void assertEmptyViewElement(IPointViewInput newInput) {
    assertNotNull(newInput);
    assertNull(newInput.getCharacterId());
    assertArrayEquals(new IPointEntry[0], newInput.createEntries());
  }

  @Before
  public void createViewInputFactory() throws Exception {
    this.viewInputFactory = new PointViewInputFactory();
  }
  
  @Test
  public void nullInputProviderReturnsEmptyViewInput() throws Exception {
    IPointViewInput newInput = viewInputFactory.createEditorInput(null, null);
    assertEmptyViewElement(newInput);
  }
  
  @Test
  public void nonCharacterEditorInputReturnsEmptyViewInput() throws Exception {
    IEditorInput editorInput = EasyMock.createStrictMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(null);
    IEditorInputProvider inputProvider = EasyMock.createStrictMock(IEditorInputProvider.class);
    EasyMock.expect(inputProvider.getEditorInput()).andReturn(editorInput);
    EasyMock.replay(inputProvider, editorInput);
    IPointViewInput newInput = viewInputFactory.createEditorInput(inputProvider, null);
    assertEmptyViewElement(newInput);
    EasyMock.verify(inputProvider, editorInput);
  }
}