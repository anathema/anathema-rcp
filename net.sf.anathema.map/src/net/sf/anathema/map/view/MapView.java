package net.sf.anathema.map.view;

import gis.gisterm.gcore.WorldBox;

import java.awt.Frame;
import java.io.File;

import net.sf.anathema.map.view.data.GisDataDirectory;
import net.sf.anathema.map.view.data.StandardLayerFactory;
import net.sf.anathema.map.view.gisterm.AnathemaGisView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.disy.gis.gisterm.map.BasicMapModel;
import de.disy.gis.gisterm.map.theme.LayerTheme;

public class MapView extends ViewPart {

  @Override
  public void createPartControl(Composite parent) {
    try {
      Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
      Frame bridgeFrame = SWT_AWT.new_Frame(composite);
      AnathemaGisView anathemaGisView = new AnathemaGisView();
      final BasicMapModel mapModel = new BasicMapModel();
      GisDataDirectory gisDataDirectory = new GisDataDirectory();
      gisDataDirectory.setDirectory(new File("H:\\RCP\\trunk\\Map\\gisdata\\")); //$NON-NLS-1$
      StandardLayerFactory layerFactory = new StandardLayerFactory(gisDataDirectory);
      mapModel.getThemeModel().addTheme(new LayerTheme(layerFactory.createXeriarRasterLayer()));
      mapModel.getThemeModel().addTheme(new LayerTheme(layerFactory.createSketchLayer()));
      mapModel.getThemeModel().addTheme(new LayerTheme(layerFactory.createFeatureLayer()));
      mapModel.getViewProperties().setSuggestedWorldBox(new WorldBox(5050, 6900, 8350, 4150));
      anathemaGisView.initGisView(mapModel);
      bridgeFrame.add(anathemaGisView.getComponent());
    }
    catch (Exception e) {
      // TODO Fehlerhandling
      e.printStackTrace();
    }
  }

  @Override
  public void setFocus() {
    // nothing to do
  }
}