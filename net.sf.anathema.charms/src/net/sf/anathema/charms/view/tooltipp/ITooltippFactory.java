package net.sf.anathema.charms.view.tooltipp;

import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.draw2d.IFigure;

public interface ITooltippFactory {

  public IFigure createFor(ICharmId charmId);

}