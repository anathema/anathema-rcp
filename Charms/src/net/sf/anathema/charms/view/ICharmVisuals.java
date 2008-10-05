package net.sf.anathema.charms.view;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.zest.core.widgets.GraphNode;

public interface ICharmVisuals extends IDisposable {

  public void update(GraphNode node);

  public void connect(ZestView zestView);
}