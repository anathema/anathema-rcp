package net.sf.anathema.campaign.plot.repository;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;

public class PlotPart implements IPlotPart {

  private final List<IPlotPart> children = new ArrayList<IPlotPart>();
  private String repositoryId;
  private final PlotUnit plotUnit;
  private final IPlotPart parent;

  public PlotPart(String repositoryId, PlotUnit plotUnit, IPlotPart parent) {
    this.repositoryId = repositoryId;
    this.plotUnit = plotUnit;
    this.parent = parent;
  }

  @Override
  public IPlotPart[] getChildren() {
    return children.toArray(new IPlotPart[children.size()]);
  }

  @Override
  public String getRepositoryId() {
    return repositoryId;
  }

  public PlotPart addChild(String childId) {
    PlotUnit unitSuccessor = plotUnit.getSuccessor();
    Ensure.ensureArgumentNotNull("No child can be added for plot unit:" + plotUnit, unitSuccessor); //$NON-NLS-1$
    PlotPart plotPart = new PlotPart(childId, unitSuccessor, this);
    children.add(plotPart);
    return plotPart;
  }

  @Override
  public PlotUnit getPlotUnit() {
    return plotUnit;
  }

  @Override
  public IPlotPart getParent() {
    return parent;
  }
}