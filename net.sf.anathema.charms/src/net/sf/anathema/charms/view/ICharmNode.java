package net.sf.anathema.charms.view;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public interface ICharmNode {

  public String getCharmId();
  
  public Display getDisplay();

  public void setColor(Color color);
}