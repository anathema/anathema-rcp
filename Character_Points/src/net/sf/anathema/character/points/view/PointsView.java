package net.sf.anathema.character.points.view;

import net.sf.anathema.view.valuelist.IUpdatable;

public class PointsView extends AbstractValueListView implements IUpdatable {
  
  public PointsView() {
    super(new PointViewInputStore(), new PointViewUpdateHandler());
  }
}