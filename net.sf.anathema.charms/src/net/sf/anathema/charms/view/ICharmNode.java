package net.sf.anathema.charms.view;

import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public interface ICharmNode {

  public ICharmId getCharmId();

  public Display getDisplay();

  public void setColor(Color color);
}