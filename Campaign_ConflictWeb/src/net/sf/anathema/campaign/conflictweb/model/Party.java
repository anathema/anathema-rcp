package net.sf.anathema.campaign.conflictweb.model;

import org.eclipse.draw2d.geometry.Point;

public class Party implements IParty {

  private Point position;
  
  public Party(Point position) {
    this.position = position;
  }

  @Override
  public Point getPosition() {
    return position;
  }
}