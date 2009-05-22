package net.sf.anathema.character.experience.points;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractEntryTextEditorControl;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;
import net.sf.anathema.character.experience.IExperiencePoints;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ExperiencePointsEditor extends AbstractCharacterModelEditorPart<IExperiencePoints> {

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractEntryTextEditorControl(this) {
      private static final String INSTRUCTION = "Type an experience entry and press 'Enter', e.g. '11 Crushing the Wyld Hunt'.";

      @Override
      protected String getInstruction() {
        return INSTRUCTION;
      }

      @Override
      protected SelectionListener createSelectionListener() {
        return new SelectionAdapter() {
          @Override
          public void widgetDefaultSelected(SelectionEvent e) {
            ExperiencePointsEditorInput editorInput = getExperienceEditorInput();
            String entryText = getInputText().getText();
            editorInput.addEntry(entryText);
          }
        };
      }

      @Override
      protected void addTable(FormToolkit toolkit, Composite body) {
        Table table = createTable(toolkit, body);
        createPointsColumn(table);
        createCommentColumn(table);
        createTableViewer(table);
      }

      private Table createTable(FormToolkit toolkit, Composite body) {
        Table table = toolkit.createTable(body, SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);
        table.setLayoutData(createTableGridData());
        table.setHeaderVisible(true);
        return table;
      }

      private GridData createTableGridData() {
        return new GridData(SWT.FILL, SWT.TOP, true, true);
      }

      private void createTableViewer(final Table table) {
        final TableViewer tableViewer = new TableViewer(table);
        tableViewer.setContentProvider(new ExperienceContentProvider());
        tableViewer.setLabelProvider(new ExperiencePointsLabelProvider());
        tableViewer.setInput(getPersistableEditorInput().getItem());
        getPersistableEditorInput().getItem().addChangeListener(new IChangeListener() {
          @Override
          public void stateChanged() {
            tableViewer.refresh();
            table.getParent().layout();
          }
        });
        final ExperiencePointsEditorInput editorInput = getExperienceEditorInput();
        tableViewer.addDoubleClickListener(new EditEntryDoubleClickListener(editorInput));
      }

      private void createCommentColumn(Table table) {
        TableColumn commentColumn = new TableColumn(table, SWT.LEFT);
        commentColumn.setText("Comment");
        commentColumn.setWidth(600);
        commentColumn.setResizable(true);
      }

      private void createPointsColumn(Table table) {
        TableColumn valueColumn = new TableColumn(table, SWT.RIGHT);
        valueColumn.setText("Points");
        valueColumn.setWidth(50);
      }
    };
  }

  protected ExperiencePointsEditorInput getExperienceEditorInput() {
    return (ExperiencePointsEditorInput) getPersistableEditorInput();
  }
}