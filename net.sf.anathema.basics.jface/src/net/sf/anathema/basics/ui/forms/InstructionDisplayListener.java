package net.sf.anathema.basics.ui.forms;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Text;

public final class InstructionDisplayListener extends MouseAdapter implements FocusListener, IDisposable {
  private static final String EMPTY_TEXT = ""; //$NON-NLS-1$

  private final Text textfield;
  private final String instruction;

  public static IDisposable Connect(Text textfield, String instruction) {
    InstructionDisplayListener instructionListener = new InstructionDisplayListener(textfield, instruction);
    textfield.addFocusListener(instructionListener);
    textfield.addMouseListener(instructionListener);
    return instructionListener;
  }

  private InstructionDisplayListener(Text textfield, String instruction) {
    this.textfield = textfield;
    this.instruction = instruction;
  }

  @Override
  public void focusGained(FocusEvent e) {
    hideInstruction();
  }

  @Override
  public void focusLost(FocusEvent e) {
    showInstruction();
  }

  @Override
  public void mouseDown(MouseEvent e) {
    hideInstruction();
  }

  private void hideInstruction() {
    if (textfield.getText().equals(instruction)) {
      textfield.setText(EMPTY_TEXT);
    }
  }

  private void showInstruction() {
    if (textfield.getText().equals(EMPTY_TEXT)) {
      textfield.setText(instruction);
    }
  }

  @Override
  public void dispose() {
    textfield.removeMouseListener(this);
    textfield.removeFocusListener(this);
  }
}