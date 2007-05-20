package net.sf.anathema.campaign.plot.item;

import java.util.ArrayList;
import java.util.List;


public class PlotPart implements IPlotPart {

  private final List<IPlotPart> children = new ArrayList<IPlotPart>();
  private String repositoryId;

  public PlotPart(String repositoryId) {
    this.repositoryId = repositoryId;
  }
  
  @Override
  public IPlotPart[] getChildren() {
    return children.toArray(new IPlotPart[children.size()]);
  }

  @Override
  public String getRepositoryId() {
    return repositoryId;
  }

  public void addChild(PlotPart newPart) {
    children.add(newPart);
  }
}