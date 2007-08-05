package net.sf.anathema.character.freebies;

import net.sf.anathema.view.valuelist.AbstractValueListView;

public class FreebiesView extends AbstractValueListView {

  public FreebiesView() {
    super(new FreebiesViewInputStore(new FreebiePointEntryFactory()), new FreebiesViewUpdateHandler());
  }
}