package net.sf.anathema.campaign.plot.item;

import net.sf.anathema.lib.util.IIdentificate;

// Story, Episode, Scene
public interface IPlotTimeUnit extends IIdentificate {

  public String getId();

  public IPlotTimeUnit getSuccessor();

  public boolean hasSuccessor();
}