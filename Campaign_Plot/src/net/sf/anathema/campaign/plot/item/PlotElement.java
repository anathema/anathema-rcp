package net.sf.anathema.campaign.plot.item;

import net.sf.anathema.basics.item.data.IItemDescription;
import net.sf.anathema.basics.item.data.ItemDescription;

public class PlotElement extends PlotElementContainer implements IPlotElement {

  private final IItemDescription itemDescription;

  public PlotElement(PlotIDProvider provider, IPlotTimeUnit unit, String name) {
    super(provider, unit);
    this.itemDescription = new ItemDescription(name);
  }

  public PlotElement(PlotIDProvider provider, IPlotTimeUnit unit, IItemDescription description, String id) {
    super(provider, unit, id);
    this.itemDescription = description;
  }

  public IItemDescription getDescription() {
    return itemDescription;
  }

  @Override
  public String toString() {
    return getDescription().getName().getText();
  }

  public boolean isDirty() {
    return itemDescription.isDirty();
  }

  public void setClean() {
    itemDescription.setClean();
  }
}