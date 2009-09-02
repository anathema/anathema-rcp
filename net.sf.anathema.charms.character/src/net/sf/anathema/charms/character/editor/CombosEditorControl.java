package net.sf.anathema.charms.character.editor;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.charms.character.combo.IComboModel;
import net.sf.anathema.charms.character.editor.combo.ComboInputViewFactory;
import net.sf.anathema.charms.character.editor.combo.ComboInputWidgets;
import net.sf.anathema.charms.character.editor.combo.LearnedComboControl;
import net.sf.anathema.charms.character.editor.dnd.AddCharmDropListener;
import net.sf.anathema.charms.character.editor.dnd.CharmTableDragListener;
import net.sf.anathema.charms.character.editor.dnd.RemoveCharmDropListener;
import net.sf.anathema.charms.character.editor.dnd.TableViewerUpdatable;
import net.sf.anathema.charms.character.editor.table.ICharmTableInput;
import net.sf.anathema.charms.data.INameMap;
import net.sf.anathema.lib.ui.ChangeableModelDisposable;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CombosEditorControl extends AbstractItemEditorControl {

  private final class UpdateLearnedCombos implements IChangeListener {
    private final FormToolkit toolkit;
    private final Composite availableCombos;

    private UpdateLearnedCombos(FormToolkit toolkit, Composite availableCombos) {
      this.toolkit = toolkit;
      this.availableCombos = availableCombos;
    }

    @Override
    public void stateChanged() {
      INameMap charmNameMap = getComboEditorInput().getCharmNameMap();
      LearnedComboControl view = new LearnedComboControl(availableCombos, toolkit, charmNameMap);
      view.updateView(getComboModel().getCreationLearned());
    }
  }

  private static class SameInstanceInputUpdate implements IChangeListener {
    private final ICharmTableInput input;
    private final TableViewer tableViewer;

    private SameInstanceInputUpdate(ICharmTableInput input, TableViewer tableViewer) {
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

  private static final class ActivateForValidModel implements IChangeListener {
    private final Button confirmButton;
    private final ComboEditModel model;

    public ActivateForValidModel(Button confirmButton, ComboEditModel model) {
      this.confirmButton = confirmButton;
      this.model = model;
      stateChanged();
    }

    @Override
    public void stateChanged() {
      confirmButton.setEnabled(model.isValid());
    }
  }

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
    initConfirmationListening(widgets.confirmButton);
    initConfirmationActivation(widgets.confirmButton);
    initCharmTableUpdate(widgets);
    initTextualUpdates(widgets);
    initLearnedComboUpdates(toolkit, widgets.availableCombos);
  }

  private void initLearnedComboUpdates(FormToolkit toolkit, Composite availableCombos) {
    IChangeListener changeListener = new UpdateLearnedCombos(toolkit, availableCombos);
    getComboModel().addChangeListener(changeListener);
    changeListener.stateChanged();
    addDisposable(new ChangeableModelDisposable(getComboModel(), changeListener));
  }

  private IComboModel getComboModel() {
    ComboEditorInput comboEditorInput = getComboEditorInput();
    return comboEditorInput.getItem();
  }

  private void initTextualUpdates(ComboInputWidgets widgets) {
    // TODO Auto-generated method stub
  }

  private void initConfirmationActivation(Button confirmButton) {
    ComboEditorInput editorInput = getComboEditorInput();
    ComboEditModel model = editorInput.getComboEditModel();
    ActivateForValidModel listener = new ActivateForValidModel(confirmButton, model);
    model.addChangeListener(listener);
    addDisposable(new ChangeableModelDisposable(model, listener));
  }

  private void initConfirmationListening(Button confirmButton) {
    confirmButton.addSelectionListener(new SelectionListener() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        widgetDefaultSelected(e);
      }

      @Override
      public void widgetDefaultSelected(SelectionEvent e) {
        getComboEditorInput().learnCombo();
      }
    });
  }

  private void initCharmTableUpdate(ComboInputWidgets widgets) {
    ComboEditorInput comboEditorInput = getComboEditorInput();
    ICharmTableInput comboableCharms = comboEditorInput.getComboableCharms();
    ICharmTableInput comboedCharms = comboEditorInput.getComboedCharms();
    comboableCharms.addChangeListener(new SameInstanceInputUpdate(comboableCharms, widgets.availableCharms));
    comboedCharms.addChangeListener(new SameInstanceInputUpdate(comboedCharms, widgets.selectedCharms));
    addDisposable(comboableCharms);
    addDisposable(comboedCharms);
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