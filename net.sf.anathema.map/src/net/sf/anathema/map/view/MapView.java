package net.sf.anathema.map.view;

import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class MapView extends ViewPart {

  @Override
  public void createPartControl(Composite parent) {
    try {
      Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
      Frame bridgeFrame = SWT_AWT.new_Frame(composite);
      bridgeFrame.add(new SwingView().create());
    }
    catch (Exception e) {
      // TODO Fehlerhandling
      e.printStackTrace();
    }
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}