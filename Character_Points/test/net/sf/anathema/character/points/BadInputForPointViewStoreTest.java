package net.sf.anathema.character.points;

import static org.junit.Assert.*;
import net.sf.anathema.character.core.model.IModelIdentifier;
import net.sf.anathema.character.points.configuration.internal.PointConfigurationExtensionPoint;
import net.sf.anathema.character.points.view.ICharacterValueEntryFactory;
import net.sf.anathema.character.points.view.PointViewInputStore;
import net.sf.anathema.view.valuelist.IValueEntry;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.junit.Before;
import org.junit.Test;

public class BadInputForPointViewStoreTest {
  private PointViewInputStore viewInputFactory;

  private void assertEmptyViewElement(ICharacterValueEntryFactory newInput) {
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
    ICharacterValueEntryFactory newInput = viewInputFactory.getEntriesFactory(null);
    assertEmptyViewElement(newInput);
  }

  @Test
  public void nonCharacterEditorInputReturnsEmptyViewInput() throws Exception {
    IEditorInput editorInput = EasyMock.createStrictMock(IEditorInput.class);
    EasyMock.expect(editorInput.getAdapter(IModelIdentifier.class)).andReturn(null);
    EasyMock.replay(editorInput);
    ICharacterValueEntryFactory newInput = viewInputFactory.getEntriesFactory(editorInput);
    assertEmptyViewElement(newInput);
    EasyMock.verify(editorInput);
  }
}