package net.sf.anathema.editor.styledtext;

import net.disy.commons.core.model.listener.IChangeListener;

import org.eclipse.ui.IEditorPart;

public interface IStyledTextEditor extends IEditorPart {

  public void modifySelection(ITextModification modification);

  public boolean isActiveFor(ITextModification modification);

  public void addCaretChangeListener(IChangeListener changeListener);

  public void removeCaretChangeListener(IChangeListener caretChangeListener);

  public boolean isSelectionEmpty();
}