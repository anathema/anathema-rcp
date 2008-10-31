package net.sf.anathema.character.freebies.view;

import net.sf.anathema.view.valuelist.AbstractValueListView;

public class FreebiesView extends AbstractValueListView {

  public FreebiesView() {
    super(new FreebiePointInputProvider(new FreebiePointEntryFactory()), new FreebiesViewUpdateHandler());
  }
}