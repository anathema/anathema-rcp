package net.sf.anathema.basics.importwizard.control;

import static org.junit.Assert.*;
import net.disy.commons.core.model.BooleanModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

public class OpenCheckBoxFactoryTest {

  private Button button;

  @Before
  public void createButtonWithTrueBooleanModel() {
    BooleanModel booleanModel = new BooleanModel(true);
    Shell shell = new Shell();
    new OpenCheckBoxFactory("OpenLabel", booleanModel).create(shell); //$NON-NLS-1$
    button = (Button) shell.getChildren()[0];
  }

  @Test
  public void isCheckBox() throws Exception {
    assertEquals(SWT.CHECK, button.getStyle() & SWT.CHECK);
  }

  @Test
  public void initializesCheckBoxFromModel() throws Exception {
    assertTrue(button.getSelection());
  }
}