package net.sf.anathema.campaign.plot;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

import org.osgi.framework.BundleContext;

public class PlotPlugin extends AbstractAnathemaUIPlugin {

  public static final String ID = "net.sf.anathema.campaign.plot";//$NON-NLS-1$
  public static final String PLOT_EDITOR_ID = "net.sf.anathema.campaign.plot.ploteditor"; //$NON-NLS-1$
  private static PlotPlugin instance;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
  }

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