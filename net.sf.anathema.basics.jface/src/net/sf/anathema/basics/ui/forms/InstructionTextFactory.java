package net.sf.anathema.basics.ui.forms;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class InstructionTextFactory {

  private final FormToolkit toolkit;

  public InstructionTextFactory(FormToolkit toolkit) {
    this.toolkit = toolkit;
  }

  public Text create(Composite parent, SelectionListener selectionListener, String instruction) {
    Text textfield = toolkit.createText(parent, instruction);
    InstructionDisplayListener.Connect(textfield, selectionListener, instruction);
    return textfield;
  }
}