package net.sf.anathema.basics.swt.dispose;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.swt.graphics.Color;

public class ColorDisposable implements IDisposable {

  private final Color color;

  public ColorDisposable(Color color) {
    this.color = color;
  }

  @Override
  public void dispose() {
    color.dispose();
  }
}