package net.sf.anathema.editor.styledtext;

import net.sf.anathema.basics.item.editor.ErrorMessageEditorInput;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

public class StyledTextEditorObjectMother {

  public static StyledTextEditor createStyledTextEditorWithoutSelectionProvider() throws PartInitException {
    StyledTextEditor editor = new StyledTextEditor();
    IEditorSite editorSite = EasyMock.createMock(IEditorSite.class);
    EasyMock.expect(editorSite.getSelectionProvider()).andReturn(null).anyTimes();
    EasyMock.replay(editorSite);
    editor.init(editorSite, new ErrorMessageEditorInput("Pfff")); //$NON-NLS-1$
    return editor;
  }
}