package net.sf.anathema.charms.character.editor;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.charms.character.editor.combo.AddToComboListener;
import net.sf.anathema.charms.character.editor.combo.CharmTableControl;
import net.sf.anathema.charms.character.editor.combo.ComboInputViewFactory;
import net.sf.anathema.charms.character.editor.combo.ComboInputWidgets;
import net.sf.anathema.charms.character.editor.combo.ComboListControl;
import net.sf.anathema.charms.character.editor.combo.ComboTextControl;
import net.sf.anathema.charms.character.editor.combo.ConfirmationControl;
import net.sf.anathema.charms.character.editor.combo.RemoveFromComboListener;
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

  private ComboEditorInput getEditorInput() {
    return (ComboEditorInput) getEditor().getPersistableEditorInput();
  }

  @Override
  protected void createPartControl(FormToolkit toolkit, Composite body) {
    body.setLayout(new GridLayout(1, false));
    ComboInputWidgets widgets = new ComboInputViewFactory(getEditorInput(), toolkit).create(body);
    initAddDragAndDrop(widgets.availableCharms, widgets.selectedCharms);
    initDoubleClickEdit(widgets);
    initRemoveDragAndDrop(widgets.availableCharms, widgets.selectedCharms, body);
    new CharmTableControl(getEditorInput(), this).initControl(widgets);
    new ComboListControl(getEditorInput(), this).initControl(widgets);
    new ComboTextControl(getEditorInput(), this).initControl(widgets);
    new ConfirmationControl(getEditorInput(), this).initControl(widgets);
  }

  private void initDoubleClickEdit(ComboInputWidgets widgets) {
    ComboEditorInput editorInput = getEditorInput();
    ComboEditModel comboEditModel = editorInput.getComboEditModel();
    widgets.availableCharms.addDoubleClickListener(new AddToComboListener(comboEditModel));
    widgets.selectedCharms.addDoubleClickListener(new RemoveFromComboListener(comboEditModel));
  }

  private void initAddDragAndDrop(TableViewer availableTable, TableViewer comboTable) {
    createTableDragSource(availableTable);
    DropTarget target = new DropTarget(comboTable.getControl(), DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
    target.setTransfer(types);
    target.addDropListener(new AddCharmDropListener(new TableViewerUpdatable(comboTable), getEditorInput()));
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
    target.addDropListener(new RemoveCharmDropListener(new TableViewerUpdatable(comboTable), getEditorInput()));
  }

  private void createTableDragSource(TableViewer sourceTable) {
    DragSource source = new DragSource(sourceTable.getControl(), DND.DROP_MOVE | DND.DROP_COPY);
    source.setTransfer(types);
    source.addDragListener(new CharmTableDragListener(sourceTable));
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}