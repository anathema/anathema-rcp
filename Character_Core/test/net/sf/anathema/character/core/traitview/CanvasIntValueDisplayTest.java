package net.sf.anathema.character.core.traitview;

import static org.junit.Assert.*;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.junit.Before;
import org.junit.Test;

public class CanvasIntValueDisplayTest {

  private static final int MAX_VALUE = 5;
  private CanvasIntValueDisplay valueDisplay;
  private Image image;
  private Shell parent;
  private Color color;

  @Before
  public void createValueDisplayWithMaxValue5() throws Exception {
    parent = new Shell();
    image = new Image(parent.getDisplay(), 7, 7);
    color = new Color(parent.getDisplay(), 255, 255, 255);
  }

  @Test
  public void noLayoutOnSettingSameMaxValue() throws Exception {
    valueDisplay = new CanvasIntValueDisplay(color, parent, image, image, MAX_VALUE) {
      @Override
      protected void refreshLayout() {
        fail();
      }
    };
    valueDisplay.setMaxValue(MAX_VALUE);
  }

  @Test
  public void layoutOnSettingDifferntMaxValue() throws Exception {
    final boolean[] refresh = new boolean[] { false };
    valueDisplay = new CanvasIntValueDisplay(color, parent, image, image, MAX_VALUE) {
      @Override
      protected void refreshLayout() {
        refresh[0] = true;
      }
    };
    valueDisplay.setMaxValue(MAX_VALUE + 2);
    assertTrue(refresh[0]);
  }
}