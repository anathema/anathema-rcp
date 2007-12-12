package net.sf.anathema.map;

import java.io.File;

import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;

import org.osgi.framework.BundleContext;

public class MapPlugin extends AbstractAnathemaUIPlugin {

  private static MapPlugin instance;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    System.err.println(new File(".").getAbsolutePath());
//    LayerPanel.popupFactory = new AnathemaLayerPopupFactory();
//    SmartFileChooser.getInstance().setFileChooserProvider(new DefaultFileChooserProvider());
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
    return instance;
  }
}