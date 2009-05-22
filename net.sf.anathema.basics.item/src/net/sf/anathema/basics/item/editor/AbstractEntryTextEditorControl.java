package net.sf.anathema.basics.item.editor;

import net.sf.anathema.basics.ui.forms.InstructionTextFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
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
  protected void createPartControl(FormToolkit toolkit, Composite body) {
    addEntryText(toolkit, body);
    addTable(toolkit, body);
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