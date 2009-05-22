package net.sf.anathema.basics.ui.forms;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@SuppressWarnings("nls")
public class InstructionDisplayListener_Test {

  private class TestSelectionListener implements SelectionListener {

    public String defaultText;
    public String text;

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
      defaultText = textfield.getText();
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
      text = textfield.getText();
    }
  }

  private TestSelectionListener selectionListener;

  private static final String instruction = "Horst soll spielen.";
  private static Shell shell;
  private Text textfield;
  private Text focusCompetitor;
  private InstructionDisplayListener listener;

  @BeforeClass
  public static void openShell() throws Exception {
    shell = new Shell();
    shell.open();
  }

  @AfterClass
  public static void disposeShell() throws Exception {
    shell.dispose();
  }

  @Before
  public void createTextField() throws Exception {
    focusCompetitor = new Text(shell, SWT.NONE);
    textfield = new Text(shell, SWT.NONE);
    selectionListener = new TestSelectionListener();
    listener = InstructionDisplayListener.Connect(textfield, selectionListener, instruction);
  }

  @After
  public void removeComponents() throws Exception {
    textfield.dispose();
    focusCompetitor.dispose();
  }

  @Test
  public void initializesTextWithInstruction() throws Exception {
    assertThat(textfield.getText(), is(instruction));
  }

  @Test
  public void clearsInstructionOnFocusGain() throws Exception {
    textfield.setFocus();
    assertThat(textfield.getText().isEmpty(), is(true));
  }

  @Test
  public void restoresInstructionOnFocusLost() throws Exception {
    textfield.setFocus();
    focusCompetitor.setFocus();
    assertThat(textfield.getText(), is(instruction));
  }

  @Test
  public void keepsUserEntryOnFocusLost() throws Exception {
    textfield.setFocus();
    textfield.setText("My Entry");
    focusCompetitor.setFocus();
    assertThat(textfield.getText(), is("My Entry"));
  }

  @Test
  public void removesInstructionOnMouseDown() throws Exception {
    listener.mouseDown(null);
    assertThat(textfield.getText().isEmpty(), is(true));
  }

  @Test
  public void clearsTextAfterDelegatingDefaultSelected() throws Exception {
    textfield.setText("My darling Balentine");
    listener.widgetDefaultSelected(null);
    assertThat(selectionListener.defaultText, is("My darling Balentine"));
    assertThat(textfield.getText().isEmpty(), is(true));
  }

  @Test
  public void clearsTextAfterDelegatingSelected() throws Exception {
    textfield.setText("My darling Valentine");
    listener.widgetSelected(null);
    assertThat(selectionListener.text, is("My darling Valentine"));
    assertThat(textfield.getText().isEmpty(), is(true));
  }
}