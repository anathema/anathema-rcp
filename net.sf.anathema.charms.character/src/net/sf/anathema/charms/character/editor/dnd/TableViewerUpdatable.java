package net.sf.anathema.charms.character.editor.dnd;

import net.sf.anathema.lib.ui.IUpdatable;

import org.eclipse.jface.viewers.TableViewer;

public final class TableViewerUpdatable implements IUpdatable {
  private final TableViewer tableViewer;

  public TableViewerUpdatable(TableViewer tableViewer) {
    this.tableViewer = tableViewer;
  }

  @Override
  public void update() {
    tableViewer.setInput(tableViewer.getInput());
    tableViewer.getTable().getParent().layout();
  }
}