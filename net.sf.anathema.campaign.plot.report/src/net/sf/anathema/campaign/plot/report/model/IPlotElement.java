package net.sf.anathema.campaign.plot.report.model;

import net.sf.anathema.basics.item.text.ITitledText;

public interface IPlotElement {

  public IPlotElement[] getChildren();

  public ITitledText getContent();
}