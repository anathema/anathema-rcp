package net.sf.anathema.charms.character.editor.combo;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.charms.character.editor.ComboEditorInput;
import net.sf.anathema.charms.character.editor.table.ICharmTableInput;
import net.sf.anathema.lib.ui.AggregatedDisposable;

import org.eclipse.jface.viewers.TableViewer;

public class CharmTableControl {

  public static class TableInputUpdate implements IChangeListener {
    private final ICharmTableInput input;
    private final TableViewer tableViewer;

    public TableInputUpdate(ICharmTableInput input, TableViewer tableViewer) {
      this.input = input;
      this.tableViewer = tableViewer;
    }

    @Override
    public void stateChanged() {
      if (tableViewer.getContentProvider() != null) {
        tableViewer.setInput(input);
      }
    }
  }

  private final ComboEditorInput editorInput;
  private final AggregatedDisposable disposable;

  public CharmTableControl(ComboEditorInput editorInput, AggregatedDisposable disposable) {
    this.editorInput = editorInput;
    this.disposable = disposable;
  }

  public void initControl(ComboInputWidgets widgets) {
    initTableUpdate(editorInput.getComboedCharms(), widgets.selectedCharms);
    initTableUpdate(editorInput.getComboableCharms(), widgets.availableCharms);
  }

  private void initTableUpdate(ICharmTableInput comboedCharms, TableViewer selectedCharms) {
    comboedCharms.addChangeListener(new TableInputUpdate(comboedCharms, selectedCharms));
    disposable.addDisposable(comboedCharms);
  }
}