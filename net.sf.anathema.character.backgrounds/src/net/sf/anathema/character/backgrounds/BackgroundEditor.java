package net.sf.anathema.character.backgrounds;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.item.editor.AbstractItemEditorControl;
import net.sf.anathema.basics.item.editor.IEditorControl;
import net.sf.anathema.character.core.editors.AbstractCharacterModelEditorPart;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class BackgroundEditor extends AbstractCharacterModelEditorPart<IBackgroundModel> {

  private static final String EMPTY_TEXT = "";
  private static final String DEFAULT_TEXT = "Type a background name and press 'Enter'";

  @Override
  protected IEditorControl createItemEditorControl() {
    return new AbstractItemEditorControl(this) {

      private Text entry;

      @Override
      public void createPartControl(final Composite parent) {
        final BackgroundEditorInput editorInput = (BackgroundEditorInput) getEditorInput();
        final FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        Composite container = createParentContainer(parent, toolkit);
        createBackgroundEntry(editorInput, toolkit, container);
        createBackgroundTable(editorInput, toolkit, container);
      }

      private Composite createParentContainer(final Composite parent, final FormToolkit toolkit) {
        Form form = toolkit.createForm(parent);
        toolkit.decorateFormHeading(form);
        form.setText(getEditorInput().getName());
        final Composite container = form.getBody();
        container.setLayout(new GridLayout(1, false));
        return container;
      }

      private void createBackgroundEntry(
          final BackgroundEditorInput editorInput,
          final FormToolkit toolkit,
          final Composite container) {
        entry = toolkit.createText(container, DEFAULT_TEXT);
        entry.addSelectionListener(new SelectionListener() {
          @Override
          public void widgetDefaultSelected(SelectionEvent e) {
            editorInput.getItem().addBackground(entry.getText());
          }

          @Override
          public void widgetSelected(SelectionEvent e) {
            // nothing
          }
        });
        entry.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            if (entry.getText().equals(DEFAULT_TEXT)) {
              entry.setText(EMPTY_TEXT);
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (entry.getText().equals(EMPTY_TEXT)) {
              entry.setText(DEFAULT_TEXT);
            }
          }
        });
      }

      private void createBackgroundTable(
          final BackgroundEditorInput editorInput,
          final FormToolkit toolkit,
          final Composite container) {
        final Table table = toolkit.createTable(container, SWT.BORDER | SWT.SINGLE);
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        editorInput.getItem().addChangeListener(new IChangeListener() {
          @Override
          public void stateChanged() {
            if (!entry.getText().equals(EMPTY_TEXT)) {
              addBackgroundToTable(table, entry.getText());
            }
            entry.setText(EMPTY_TEXT);
          }
        });
        for (String background : getPersistableEditorInput().getItem().getBackgrounds()) {
          addBackgroundToTable(table, background);
        }
      }

      @Override
      public void setFocus() {
        // entry.setFocus();
      }
    };
  }

  private void addBackgroundToTable(final Table table, String background) {
    TableItem item = new TableItem(table, SWT.NONE);
    item.setText(background);
  }
}