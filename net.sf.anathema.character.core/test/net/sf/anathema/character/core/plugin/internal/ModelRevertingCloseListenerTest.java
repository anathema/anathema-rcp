package net.sf.anathema.character.core.plugin.internal;

import net.sf.anathema.basics.item.editor.ErrorMessageEditorInput;
import net.sf.anathema.basics.item.editor.TestPersistableItemEditorPart;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorSite;
import org.junit.Test;

public class ModelRevertingCloseListenerTest {

  @Test
  public void noExceptionOnClosingErrorEditorPart() throws Exception {
    ModelRevertingCloseListener closeListener = new ModelRevertingCloseListener();
    TestPersistableItemEditorPart editorPart = new TestPersistableItemEditorPart();
    editorPart.init(EasyMock.createMock(IEditorSite.class), new ErrorMessageEditorInput("ERROA")); //$NON-NLS-1$
    closeListener.partClosed(editorPart);
  }
}