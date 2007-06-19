package net.sf.anathema.campaign.plot.repository;

public enum PlotUnit {

  Scene(null), Episode(PlotUnit.Scene), Story(PlotUnit.Episode), Plot(PlotUnit.Story);

  private final PlotUnit successor;

  private PlotUnit(PlotUnit successor) {
    this.successor = successor;
  }

  public PlotUnit getSuccessor() {
    return successor == null ? Scene : successor;
  }

  public String getPersistenceString() {
    return name();
  }
}