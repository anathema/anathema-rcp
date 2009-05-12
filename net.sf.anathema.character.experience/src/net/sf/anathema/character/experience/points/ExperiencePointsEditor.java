package net.sf.anathema.character.experience.points;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;
import net.sf.anathema.character.experience.IExperiencePoints;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ExperiencePointsEditor extends AbstractCharacterModelEditorPart<IExperiencePoints> {

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      @Override
      public void setFocus() {
        // nothing to do
      }

      @Override
      public void createPartControl(Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        Composite body = createFormBody(parent, toolkit);
        toolkit.createLabel(body, "New entry:", SWT.NONE);
        final Text inputText = toolkit.createText(body, "", SWT.NONE);
        inputText.addSelectionListener(new SelectionAdapter() {
          @Override
          public void widgetDefaultSelected(SelectionEvent e) {
            ExperiencePointsEditorInput editorInput = (ExperiencePointsEditorInput) getPersistableEditorInput();
            editorInput.addEntry(inputText.getText());
          }
        });
        inputText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        Table table = createTable(toolkit, body);
        createPointsColumn(table);
        createCommentColumn(table);
        createTableViewer(table);
      }

      private Composite createFormBody(Composite parent, FormToolkit toolkit) {
        Form form = toolkit.createForm(parent);
        Composite body = form.getBody();
        body.setLayout(new GridLayout(2, false));
        return body;
      }

      private Table createTable(FormToolkit toolkit, Composite body) {
        Table table = toolkit.createTable(body, SWT.BORDER | SWT.V_SCROLL);
        table.setLayoutData(createTableGridData());
        table.setHeaderVisible(true);
        return table;
      }

      private GridData createTableGridData() {
        GridData tableData = new GridData(SWT.FILL, SWT.TOP, true, true);
        tableData.horizontalSpan = 2;
        return tableData;
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
}