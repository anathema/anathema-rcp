package net.sf.anathema.charms.character.editor.dnd;

import net.sf.anathema.charms.character.editor.ComboEditorInput;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.ui.IUpdatable;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;

public final class RemoveCharmDropListener extends DropTargetAdapter {
  private final IUpdatable updatable;
  private final ComboEditorInput editorInput;

  public RemoveCharmDropListener(IUpdatable updatable, ComboEditorInput editorInput) {
    this.updatable = updatable;
    this.editorInput = editorInput;
  }

  @Override
  public void dragOver(DropTargetEvent event) {
    event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
  }

  @Override
  public void drop(DropTargetEvent event) {
    if (!isSupportedDrop(event)) {
      return;
    }
    IStructuredSelection selection = (IStructuredSelection) event.data;
    for (Object element : selection.toList()) {
      editorInput.getComboEditModel().removeCharmFromCombo((ICharmId) element);
    }
    updatable.update();
  }

  private boolean isSupportedDrop(DropTargetEvent event) {
    if (!(event.data instanceof IStructuredSelection)) {
      return false;
    }
    IStructuredSelection selection = (IStructuredSelection) event.data;
    for (Object object : selection.toList()) {
      if (!(object instanceof ICharmId)) {
        return false;
      }
    }
    return true;
  }
}