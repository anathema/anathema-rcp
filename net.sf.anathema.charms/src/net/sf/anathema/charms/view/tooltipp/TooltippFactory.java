package net.sf.anathema.charms.view.tooltipp;

import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

public class TooltippFactory implements ITooltippFactory {

  public IFigure createFor(ICharmId charmId) {
    return new Label("Found: \n" + charmId.getId());
  }
}