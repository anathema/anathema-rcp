package net.sf.anathema.map.view.gisterm;

import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class CoordinateView implements ICoordinateView {

  private final JLabel label = new TextPreferredSizeLabel(" 00000000000000000000", 6, 5); //$NON-NLS-1$
  private final NumberFormat numberFormat;

  public CoordinateView(NumberFormat numberFormat) {
    Ensure.ensureArgumentNotNull(numberFormat);
    this.numberFormat = numberFormat;
  }

  public void setCoordinate(double x, double y) {
    label.setText(numberFormat.format(x) + "  " + numberFormat.format(y)); //$NON-NLS-1$
  }

  public JComponent getComponent() {
    return label;
  }
}