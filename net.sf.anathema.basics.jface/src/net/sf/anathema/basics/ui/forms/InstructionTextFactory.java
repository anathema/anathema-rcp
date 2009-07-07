package net.sf.anathema.basics.ui.forms;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class InstructionTextFactory {

  private final FormToolkit toolkit;
  private final SelectionAdapter nullListener = new SelectionAdapter() {
    // nothing to do
  };

  public InstructionTextFactory(FormToolkit toolkit) {
    this.toolkit = toolkit;
  }

  public Text create(Composite parent, String instruction) {
    return create(parent, nullListener, instruction);
  }

  public Text create(Composite parent, String instruction, int style) {
    Text textfield = toolkit.createText(parent, instruction, style);
    InstructionDisplayListener.Connect(textfield, nullListener, instruction);
    return textfield;
  }

  public Text create(Composite parent, SelectionListener selectionListener, String instruction) {
    Text textfield = toolkit.createText(parent, instruction);
    InstructionDisplayListener.Connect(textfield, selectionListener, instruction);
    return textfield;
  }
}