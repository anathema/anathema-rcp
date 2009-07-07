package net.sf.anathema.charms.character.editor;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.charms.character.editor.combo.ComboInputViewFactory;
import net.sf.anathema.charms.character.editor.combo.ComboInputWidgets;
import net.sf.anathema.charms.character.editor.dnd.AddCharmDropListener;
import net.sf.anathema.charms.character.editor.dnd.CharmTableDragListener;
import net.sf.anathema.charms.character.editor.dnd.RemoveCharmDropListener;
import net.sf.anathema.charms.character.editor.dnd.TableViewerUpdatable;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CombosEditorControl extends AbstractItemEditorControl {

  private final Transfer[] types = new Transfer[] { LocalSelectionTransfer.getTransfer() };

  public CombosEditorControl(CombosEditor combosEditor) {
    super(combosEditor);
  }

  @Override
  protected void createPartControl(FormToolkit toolkit, Composite body) {
    body.setLayout(new GridLayout(1, false));
    ComboInputWidgets widgets = new ComboInputViewFactory(getComboEditorInput(), toolkit).create(body);
    initAddDragAndDrop(widgets.availableCharms, widgets.selectedCharms);
    initRemoveDragAndDrop(widgets.availableCharms, widgets.selectedCharms, body);
  }

  private void initAddDragAndDrop(TableViewer availableTable, TableViewer comboTable) {
    createTableDragSource(availableTable);
    DropTarget target = new DropTarget(comboTable.getControl(), DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
    target.setTransfer(types);
    target.addDropListener(new AddCharmDropListener(new TableViewerUpdatable(comboTable), getComboEditorInput()));
  }

  private void initRemoveDragAndDrop(TableViewer availableTable, TableViewer comboTable, Composite body) {
    createTableDragSource(comboTable);
    createRemoveDropListener(comboTable, availableTable.getControl());
    Composite parent = availableTable.getTable().getParent();
    do {
      createRemoveDropListener(comboTable, availableTable.getTable().getParent());
      parent = parent.getParent();
    }
    while (parent != body);
  }

  private void createRemoveDropListener(TableViewer comboTable, Control control) {
    DropTarget target = new DropTarget(control, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
    target.setTransfer(types);
    target.addDropListener(new RemoveCharmDropListener(new TableViewerUpdatable(comboTable), getComboEditorInput()));
  }

  private void createTableDragSource(TableViewer sourceTable) {
    DragSource source = new DragSource(sourceTable.getControl(), DND.DROP_MOVE | DND.DROP_COPY);
    source.setTransfer(types);
    source.addDragListener(new CharmTableDragListener(sourceTable));
  }

  private ComboEditorInput getComboEditorInput() {
    return (ComboEditorInput) getEditor().getPersistableEditorInput();
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}