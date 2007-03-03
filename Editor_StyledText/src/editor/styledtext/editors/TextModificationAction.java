package editor.styledtext.editors;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class TextModificationAction extends Action implements IStyledTextAction {

  private IStyledTextEditor editor;
  private final SelectionListener selectionListener = new SelectionListener() {
    public void widgetSelected(SelectionEvent e) {
      updateEnabled();
    }

    public void widgetDefaultSelected(SelectionEvent e) {
      updateEnabled();
    }

    private void updateEnabled() {
      setEnabled(!editor.isEmptySelection());
    }
  };
  private final ITextModification modification;

  public TextModificationAction(String text, ITextModification modification) {
    super(text);
    this.modification = modification;
    setEnabled(false);
  }

  public void setEditor(IStyledTextEditor editor) {
    if (this.editor != null) {
      editor.removeSelectionListener(selectionListener);
    }
    this.editor = editor;
    editor.addSelectionListener(selectionListener);
  }

  @Override
  public void run() {
    editor.modifySelection(modification);
  }
}