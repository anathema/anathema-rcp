package net.sf.anathema.map;

import de.disy.gis.gisterm.map.layer.edit.LayerGraphicsEditStrategyFactoryRegistry;
import de.disy.gis.gisterm.pro.map.layer.LayerPanel;
import de.disy.lib.gui.filechooser.DefaultFileChooserProvider;

import net.disy.commons.core.thread.IWorkQueueListener;
import net.disy.commons.swing.filechooser.SmartFileChooser;

import gis.gisterm.management.ClientQueryHandler;

import net.sf.anathema.map.view.data.SketchLayerEditStrategyFactory;
import net.sf.anathema.map.view.gisterm.AnathemaLayerPopupFactory;

public class GisTermInitializer {

  public static boolean initialized = false;

  public synchronized void initialize() {
    if (initialized) {
      return;
    }
    LayerPanel.popupFactory = new AnathemaLayerPopupFactory();
    SmartFileChooser.getInstance().setFileChooserProvider(new DefaultFileChooserProvider());
    ClientQueryHandler.init(20, new IWorkQueueListener() {
      @Override
      public void activeWorkersCountChanged(final int arg0) {
        // nothing to do
      }

      @Override
      public void waitingJobsCountChanged(final int arg0) {
        // nothing to do
      }
    });
    LayerGraphicsEditStrategyFactoryRegistry.getInstance().addFactory(
        new SketchLayerEditStrategyFactory());
    initialized = true;
  }
}
