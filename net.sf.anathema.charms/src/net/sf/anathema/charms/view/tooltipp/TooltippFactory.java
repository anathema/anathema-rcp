package net.sf.anathema.charms.view.tooltipp;

import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.display.DisplayCharm;
import net.sf.anathema.charms.extension.CharmProvidingExtensionPoint;
import net.sf.anathema.charms.tree.ICharmId;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;

public class TooltippFactory implements ITooltippFactory {

  private final ICharmDataMap dataMap;

  public TooltippFactory() {
    this.dataMap = CharmProvidingExtensionPoint.CreateCharmDataMap();
  }

  public IFigure createFor(ICharmId charmId) {
    CharmDto data = dataMap.getData(charmId);
    DisplayCharm displayData = new DisplayCharm(data);
    ConcatenateString textBuilder = new ConcatenateString("\n");
    textBuilder.concatenate("Type: " + displayData.getType());
    textBuilder.concatenate("Costs: " + displayData.getCost());
    textBuilder.concatenate("Keywords: " + displayData.getKeywords());
    textBuilder.concatenate("Sources: " + displayData.getAllSources());
    return new Label(textBuilder.create());
  }
}