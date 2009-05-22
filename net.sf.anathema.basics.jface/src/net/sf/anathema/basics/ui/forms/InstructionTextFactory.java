package net.sf.anathema.basics.ui.forms;

import net.sf.anathema.lib.ui.IDisposable;
import net.sf.anathema.lib.ui.IDisposableStorage;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class InstructionTextFactory {

  private final FormToolkit toolkit;
  private final IDisposableStorage disposableStorage;

  public InstructionTextFactory(FormToolkit toolkit, IDisposableStorage disposableStorage) {
    this.toolkit = toolkit;
    this.disposableStorage = disposableStorage;
  }

  public Text create(Composite parent, SelectionListener selectionListener, String instruction) {
    Text textfield = toolkit.createText(parent, instruction);
    IDisposable disposable = InstructionDisplayListener.Connect(textfield, selectionListener, instruction);
    disposableStorage.addDisposable(disposable);
    return textfield;
  }
}