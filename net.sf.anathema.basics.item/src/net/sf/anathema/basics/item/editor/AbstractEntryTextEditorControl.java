package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.ui.forms.InstructionTextFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class AbstractEntryTextEditorControl extends AbstractItemEditorControl {

  public AbstractEntryTextEditorControl(IPersistableItemEditor editor) {
    super(editor);
  }

  private Text inputText;

  @Override
  public void setFocus() {
    inputText.setFocus();
    inputText.setText(getInstruction());
    inputText.selectAll();
  }

  @Override
  public final void createPartControl(Composite parent) {
    FormToolkit toolkit = new FormToolkit(parent.getDisplay());
    Composite body = createFormBody(parent, toolkit);
    addEntryText(toolkit, body);
    addTable(toolkit, body);
  }

  private Composite createFormBody(Composite parent, FormToolkit toolkit) {
    Form form = toolkit.createForm(parent);
    toolkit.decorateFormHeading(form);
    form.setText(getEditor().getPersistableEditorInput().getName());
    Composite body = form.getBody();
    body.setLayout(new GridLayout(1, false));
    return body;
  }

  private void addEntryText(FormToolkit toolkit, Composite body) {
    SelectionListener selectionListener = createSelectionListener();
    InstructionTextFactory instructionTextFactory = new InstructionTextFactory(toolkit);
    inputText = instructionTextFactory.create(body, selectionListener, getInstruction());
    inputText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
  }

  protected abstract void addTable(FormToolkit toolkit, Composite body);

  protected abstract SelectionListener createSelectionListener();

  protected abstract String getInstruction();

  protected final Text getInputText() {
    return inputText;
  }
}