package net.sf.anathema.charms.character.editor.combo;

import net.sf.anathema.charms.character.editor.ComboEditModel;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;

public class RemoveFromComboListener implements IDoubleClickListener {
  public final ComboEditModel comboEditModel;

  public RemoveFromComboListener(ComboEditModel comboEditModel) {
    this.comboEditModel = comboEditModel;
  }

  @Override
  public void doubleClick(DoubleClickEvent event) {
    IStructuredSelection selection = (IStructuredSelection) event.getSelection();
    if (!selection.isEmpty()) {
      ICharmId charmId = (ICharmId) selection.getFirstElement();
      comboEditModel.removeCharmFromCombo(charmId);
    }
  }
}