package net.sf.anathema.charms.view;

import net.sf.anathema.lib.ui.IDisposable;

public interface ICharmVisuals extends IDisposable {

  public void update(ICharmNode node);

  public void connect(CharmSelectionControl selectionControl);
}