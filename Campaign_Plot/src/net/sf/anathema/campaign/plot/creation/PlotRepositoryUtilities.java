package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

public class PlotRepositoryUtilities {

  public static IItemType getPlotItemType() {
    return new ItemTypeProvider().getById("net.sf.anathema.itemtype.Plot"); //$NON-NLS-1$
  }
}