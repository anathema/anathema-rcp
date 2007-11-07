package net.sf.anathema.basics.item.editor;

import static org.junit.Assert.*;

import org.eclipse.swt.widgets.Shell;
import org.junit.Test;

public class TestPersistableItemEditorPartTest {

  @Test
  public void togglesItemCreationFlagWhenItemsAreCreated() throws Exception {
    TestPersistableItemEditorPart editorPart = new TestPersistableItemEditorPart();
    editorPart.createPartControl(new Shell());
    assertTrue(editorPart.isItemPartCreated());
  }
}