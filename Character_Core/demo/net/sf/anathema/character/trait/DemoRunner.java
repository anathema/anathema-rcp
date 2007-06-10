package net.sf.anathema.character.trait;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class DemoRunner {

  public void run(IDemo demo) {
    try {
      Display display = new Display();
      Shell shell = new Shell(display);
      shell.setLayout(new GridLayout(1, false));
      demo.createComposite(shell);
      shell.pack();
      shell.setText(demo.getClass().getName());
      shell.open();
      while (!shell.isDisposed()) {
        if (!display.readAndDispatch())
          display.sleep();
      }
      display.dispose();
    }
    catch (Throwable throwable) {
      throwable.printStackTrace();
    }
  }
}