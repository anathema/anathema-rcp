package net.sf.anathema.map;

import gis.gisterm.management.ClientQueryHandler;
import net.disy.commons.core.thread.IWorkQueueListener;
import net.disy.commons.swing.filechooser.SmartFileChooser;
import net.sf.anathema.basics.eclipse.plugin.AbstractAnathemaUIPlugin;
import net.sf.anathema.map.view.gisterm.AnathemaLayerPopupFactory;

import org.osgi.framework.BundleContext;

import de.disy.gis.gisterm.pro.map.layer.LayerPanel;
import de.disy.lib.gui.filechooser.DefaultFileChooserProvider;

public class MapPlugin extends AbstractAnathemaUIPlugin {

  private static MapPlugin instance;

  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    LayerPanel.popupFactory = new AnathemaLayerPopupFactory();
    SmartFileChooser.getInstance().setFileChooserProvider(new DefaultFileChooserProvider());
    ClientQueryHandler.init(20, new IWorkQueueListener() {
      @Override
      public void activeWorkersCountChanged(int arg0) {
        // nothing to do
      }

      @Override
      public void waitingJobsCountChanged(int arg0) {
        // nothing to do
      }
    });
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