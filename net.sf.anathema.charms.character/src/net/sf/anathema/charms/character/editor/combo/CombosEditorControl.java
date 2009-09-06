package net.sf.anathema.charms.character.editor.combo;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.charms.character.editor.ComboEditModel;
import net.sf.anathema.charms.character.editor.ComboEditorInput;
import net.sf.anathema.charms.character.editor.CombosEditor;
import net.sf.anathema.charms.character.editor.dnd.AddCharmDropListener;
import net.sf.anathema.charms.character.editor.dnd.CharmTableDragListener;
import net.sf.anathema.charms.character.editor.dnd.RemoveCharmDropListener;
import net.sf.anathema.charms.character.editor.dnd.TableViewerUpdatable;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TableViewer;
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
    Control control = comboTable.getControl();
    AddCharmDropListener listener = new AddCharmDropListener(new TableViewerUpdatable(comboTable), getEditorInput());
    new LocalDragAndDropControl().addDropTarget(control, listener);
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
    TableViewerUpdatable updatable = new TableViewerUpdatable(comboTable);
    RemoveCharmDropListener listener = new RemoveCharmDropListener(updatable, getEditorInput());
    new LocalDragAndDropControl().addDropTarget(control, listener);
  }

  private void createTableDragSource(TableViewer sourceTable) {
    CharmTableDragListener listener = new CharmTableDragListener(sourceTable);
    new LocalDragAndDropControl().addDragSource(sourceTable.getControl(), listener);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}