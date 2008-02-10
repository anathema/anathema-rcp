package net.sf.anathema.character.report.internal.wizard;

import org.easymock.EasyMock;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class EditorPartObjectMother {

  public static IEditorPart createEditorPart(IEditorInput editorInput) {
    IEditorPart editorPart = EasyMock.createMock(IEditorPart.class);
    EasyMock.expect(editorPart.getEditorInput()).andReturn(editorInput).anyTimes();
    EasyMock.replay(editorPart);
    return editorPart;
  }

}
