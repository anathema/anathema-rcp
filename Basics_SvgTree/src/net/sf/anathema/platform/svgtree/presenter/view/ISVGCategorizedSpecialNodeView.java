package net.sf.anathema.platform.svgtree.presenter.view;

import net.sf.anathema.lib.ui.IIntValueView;


public interface ISVGCategorizedSpecialNodeView extends ISVGSpecialNodeView {

  public IIntValueView addCategory(String labelText, int maxValue, int value);
}