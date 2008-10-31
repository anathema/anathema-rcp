package net.sf.anathema.character.points.view;

import net.sf.anathema.view.valuelist.AbstractValueListView;

public class PointsView extends AbstractValueListView {

  public PointsView() {
    super(new CharacterPointsEntryProvider(), new PointViewUpdateHandler());
  }
}