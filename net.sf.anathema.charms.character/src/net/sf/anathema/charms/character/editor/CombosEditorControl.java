package net.sf.anathema.charms.character.editor;

import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.charms.character.editor.dnd.CharmDragListener;
import net.sf.anathema.charms.character.editor.dnd.CharmDropListener;
import net.sf.anathema.charms.character.editor.dnd.TableViewerUpdatable;
import net.sf.anathema.charms.character.editor.table.CharmContentProvider;
import net.sf.anathema.charms.character.editor.table.CharmNameColumn;
import net.sf.anathema.charms.character.editor.table.CharmTableLabelProvider;
import net.sf.anathema.charms.character.editor.table.ComboKeywordColumn;
import net.sf.anathema.charms.character.editor.table.ICharmTableInput;
import net.sf.anathema.charms.character.editor.table.ITableColumn;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.data.INameMap;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CombosEditorControl extends AbstractItemEditorControl {

  public CombosEditorControl(CombosEditor combosEditor) {
    super(combosEditor);
  }

  @Override
  protected void createPartControl(FormToolkit toolkit, Composite body) {
    Composite selectionCombosite = toolkit.createComposite(body);
    selectionCombosite.setLayout(new GridLayout(2, true));
    TableViewer availableTable = createAvailableCharmsTable(toolkit, selectionCombosite);
    TableViewer comboTable = createComboCharmsTable(toolkit, selectionCombosite);
    initDragAndDrop(availableTable, comboTable);
  }

  private TableViewer createAvailableCharmsTable(FormToolkit toolkit, Composite body) {
    Table table = createTable(toolkit, body);
    return createTableViewer(table, getComboEditorInput().getComboableCharms());
  }

  private TableViewer createComboCharmsTable(FormToolkit toolkit, Composite body) {
    Table table = createTable(toolkit, body);
    return createTableViewer(table, getComboEditorInput().getComboedCharms());
  }

  private void initDragAndDrop(TableViewer availableTable, TableViewer comboTable) {
    Transfer[] types = new Transfer[] { LocalSelectionTransfer.getTransfer() };
    DragSource source = new DragSource(availableTable.getControl(), DND.DROP_MOVE | DND.DROP_COPY);
    source.setTransfer(types);
    source.addDragListener(new CharmDragListener(availableTable));
    DropTarget target = new DropTarget(comboTable.getControl(), DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
    target.setTransfer(types);
    target.addDropListener(new CharmDropListener(new TableViewerUpdatable(comboTable), getComboEditorInput()));
  }

  private Table createTable(FormToolkit toolkit, Composite body) {
    Table table = toolkit.createTable(body, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
    table.setLayoutData(createTableGridData());
    table.setHeaderVisible(true);
    return table;
  }

  private void createColumn(Table table, ITableColumn column, int style) {
    TableColumn commentColumn = new TableColumn(table, style);
    commentColumn.setText(column.getHeader());
    commentColumn.setWidth(column.getWidth());
  }

  private TableViewer createTableViewer(Table table, ICharmTableInput input) {
    INameMap nameMap = getComboEditorInput().getCharmNameMap();
    ICharmDataMap dataMap = getComboEditorInput().getCharmDataMap();
    ITableColumn[] columns = new ITableColumn[] { new CharmNameColumn(nameMap), new ComboKeywordColumn(dataMap) };
    for (ITableColumn column : columns) {
      createColumn(table, column, SWT.LEFT);
    }
    TableViewer tableViewer = new TableViewer(table);
    tableViewer.setContentProvider(new CharmContentProvider());
    tableViewer.setLabelProvider(new CharmTableLabelProvider(columns));
    tableViewer.setInput(input);
    return tableViewer;
  }

  private ComboEditorInput getComboEditorInput() {
    return (ComboEditorInput) getEditor().getPersistableEditorInput();
  }

  private GridData createTableGridData() {
    return new GridData(SWT.FILL, SWT.TOP, true, true);
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}