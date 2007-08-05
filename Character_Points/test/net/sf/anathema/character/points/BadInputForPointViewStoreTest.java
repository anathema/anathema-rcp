package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.points.configuration.internal.PointConfigurationExtensionPoint;
import net.sf.anathema.character.points.view.IPointViewInput;
import net.sf.anathema.character.points.view.IValueListInputStore;
import net.sf.anathema.character.points.view.PointViewInputStore;
import net.sf.anathema.view.valuelist.IValueEntry;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class BadInputForPointViewStoreTest {
  private IValueListInputStore viewInputFactory;

  private void assertEmptyViewElement(IPointViewInput newInput) {
    assertNotNull(newInput);
    assertNull(newInput.getCharacterId());
    assertArrayEquals(new IValueEntry[0], newInput.createEntries());
  }

  @Before
  public void createViewInputFactory() throws Exception {
    this.viewInputFactory = new PointViewInputStore(new PointConfigurationExtensionPoint());
  }

  @Test
  public void nullInputProviderReturnsEmptyViewInput() throws Exception {
    IPointViewInput newInput = viewInputFactory.getViewInput(null);
    assertEmptyViewElement(newInput);
  }

  @Test
  public void nonCharacterEditorInputReturnsEmptyViewInput() throws Exception {
    IEditorInput editorInput = EasyMock.createStrictMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(null);
    EasyMock.replay(editorInput);
    IPointViewInput newInput = viewInputFactory.getViewInput(editorInput);
    assertEmptyViewElement(newInput);
    EasyMock.verify(editorInput);
  }
}