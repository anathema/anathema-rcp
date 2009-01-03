package net.sf.anathema.charms.view.tooltipp;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.extension.CharmDataExtensionPoint;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

public class TooltippFactory implements ITooltippFactory {

  private final ICharmDataMap dataMap;

  public TooltippFactory() {
    this.dataMap = new CharmDataExtensionPoint();
  }

  public IFigure createFor(ICharmId charmId) {
    CharmDto data = dataMap.getData(charmId);
    DisplayCharm displayData = new DisplayCharm(data);
    String text = "Type: " + displayData.getType() + "\n";
    text += "Keywords: " + displayData.getKeywords();
    return new Label(text);
  }
}