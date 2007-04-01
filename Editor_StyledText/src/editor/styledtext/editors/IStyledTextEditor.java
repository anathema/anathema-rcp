package editor.styledtext.editors;

import net.sf.anathema.lib.control.change.IChangeListener;

import org.eclipse.ui.IEditorPart;

public interface IStyledTextEditor extends IEditorPart {

  public void modifySelection(ITextModification modification);

  public boolean isActiveFor(ITextModification modification);

  public void addCaretChangeListener(IChangeListener changeListener);

  public void removeCaretChangeListener(IChangeListener caretChangeListener);
}