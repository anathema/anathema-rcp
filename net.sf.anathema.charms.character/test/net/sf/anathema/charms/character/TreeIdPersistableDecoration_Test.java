package net.sf.anathema.charms.character;

import static org.easymock.EasyMock.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.eclipse.ui.IMemento;
import org.junit.Before;
import org.junit.Test;

public class TreeIdPersistableDecoration_Test {

  private static final String TREE_ID = "japaneseFluffyTree"; //$NON-NLS-1$
  private DummyPersistable original;
  private TreeIdPersistableDecoration decoration;

  @Before
  public void createDecoration() throws Exception {
    original = new DummyPersistable();
    decoration = new TreeIdPersistableDecoration(original, TREE_ID);
  }
  
  @Test
  public void putsTreeIdIntoMemento() throws Exception {
    IMemento memento = createMock(IMemento.class);
    memento.putString("treeId", TREE_ID); //$NON-NLS-1$
    replay(memento);
    decoration.saveState(memento);
    verify(memento);
  }
  
  @Test
  public void callsOriginalSaveState() throws Exception {
    IMemento memento = createNiceMock(IMemento.class);
    decoration.saveState(memento);
    assertThat(original.lastSavedMemento, is(sameInstance(memento)));
  }
}