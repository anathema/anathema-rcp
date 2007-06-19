package net.sf.anathema.campaign.plot.repository;

//TODO: Maybe we could eliminate PlotUnits?
//At this point they are only necessary for I18n, which could be done via ext. points or a short XML file. 
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