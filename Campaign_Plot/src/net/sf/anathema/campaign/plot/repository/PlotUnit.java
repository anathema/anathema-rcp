package net.sf.anathema.campaign.plot.repository;

// TODO Maybe we could eliminate PlotUnits?
// At this point they are only necessary for I18n, which could be done via ext. points or a short XML file.
public enum PlotUnit {

  Scene, Episode, Story, Plot;

  public PlotUnit getSuccessor() {
    if (ordinal() == 0) {
      return Scene;
    }
    return values()[ordinal() - 1];
  }

  public String getPersistenceString() {
    return name();
  }
}