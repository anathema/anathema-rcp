package net.sf.anathema.campaign.plot;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

public class PlotPlugin extends AbstractAnathemaUIPlugin {

  public static final String PLOT_EDITOR_ID = "net.sf.anathema.campaign.plot.ploteditor"; //$NON-NLS-1$
  private static PlotPlugin instance;

  @Override
  protected void createInstance() {
    instance = this;
  }

  @Override
  protected void deleteInstance() {
    instance = null;
  }

  @Override
  protected AbstractAnathemaUIPlugin getPluginInstance() {
    return getDefaultInstance();
  }

  public static PlotPlugin getDefaultInstance() {
    return instance;
  }
}