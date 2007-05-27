package net.sf.anathema.campaign.plot.repository;

public enum PlotUnit {

  Scene(null, "Scene"),  //$NON-NLS-1$
  Episode(PlotUnit.Scene, "Episode"),  //$NON-NLS-1$
  Story(PlotUnit.Episode, "Story"),  //$NON-NLS-1$
  Plot(PlotUnit.Story,"Series"); //$NON-NLS-1$

  private final PlotUnit successor;
  private final String persistenceString;

  private PlotUnit(PlotUnit successor, String persistenceString) {
    this.successor = successor;
    this.persistenceString = persistenceString;
  }

  public PlotUnit getSuccessor() {
    return successor == null ? Scene : successor;
  }

  public String getPersistenceString() {
    return persistenceString;
  }
}