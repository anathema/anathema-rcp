package editor.styledtext.editors;

import org.eclipse.ui.IEditorPart;

public interface IStyledTextEditor extends IEditorPart {

  public void modifySelection(ITextModification modification);

  public boolean isActiveFor(ITextModification modification);
}