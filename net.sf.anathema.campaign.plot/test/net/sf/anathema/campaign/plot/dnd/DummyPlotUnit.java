package net.sf.anathema.campaign.plot.dnd;

import java.net.URL;

import net.sf.anathema.campaign.plot.repository.IPlotUnit;

public enum DummyPlotUnit implements IPlotUnit {

  Scene, Episode, Story, Plot;

  public IPlotUnit getSuccessor() {
    if (ordinal() == 0) {
      return Scene;
    }
    return values()[ordinal() - 1];
  }

  public String getName() {
    return name();
  }

  @Override
  public URL getImage() {
    return null;
  }
}