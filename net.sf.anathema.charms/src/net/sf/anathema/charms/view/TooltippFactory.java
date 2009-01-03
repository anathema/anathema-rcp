package net.sf.anathema.charms.view;

import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

public class TooltippFactory {

  public IFigure createFor(ICharmId charmId) {
    return new Label("Found: \n" + charmId.getId());
  }
}