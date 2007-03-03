package editor.styledtext.editors;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.ui.IEditorPart;

public interface IStyledTextEditor extends IEditorPart {

  public void addSelectionListener(SelectionListener selectionListener);

  public boolean isEmptySelection();

  public void modifySelection(ITextModification modification);

  public void removeSelectionListener(SelectionListener selectionListener);
}