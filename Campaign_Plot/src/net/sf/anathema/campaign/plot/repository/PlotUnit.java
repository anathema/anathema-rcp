package net.sf.anathema.campaign.plot.repository;

// TODO Maybe we could eliminate PlotUnits?
// At this point they are only necessary for I18n, which could be done via ext. points or a short XML file.
public enum PlotUnit implements IPlotUnit {

  Scene, Episode, Story, Plot;

  public IPlotUnit getSuccessor() {
    if (ordinal() == 0) {
      return Scene;
    }
    return values()[ordinal() - 1];
  }

  @Override
  public String getId() {
    return name();
  }

  public String getName() {
    return new EnumInternationalizer(Messages.class).getDisplayName(this);
  }
}