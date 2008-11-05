package net.sf.anathema.map.view;

import java.io.File;

import javax.swing.JComponent;

import de.disy.gis.gisterm.map.BasicMapModel;
import de.disy.gis.gisterm.map.theme.LayerTheme;

import gis.gisterm.gcore.WorldBox;

import net.sf.anathema.map.view.data.GisDataDirectory;
import net.sf.anathema.map.view.data.StandardLayerFactory;
import net.sf.anathema.map.view.gisterm.AnathemaGisView;

public class SwingView {

  public JComponent create() throws Exception {
    final AnathemaGisView anathemaGisView = new AnathemaGisView();
    final BasicMapModel mapModel = new BasicMapModel();
    final GisDataDirectory gisDataDirectory = new GisDataDirectory();
    gisDataDirectory.setDirectory(new File("H:\\RCP\\trunk\\net.sf.anathema.map\\gisdata\\")); //$NON-NLS-1$
    final StandardLayerFactory layerFactory = new StandardLayerFactory(gisDataDirectory);
    mapModel.getThemeModel().addTheme(new LayerTheme(layerFactory.createXeriarRasterLayer()));
    mapModel.getThemeModel().addTheme(new LayerTheme(layerFactory.createSketchLayer()));
    mapModel.getThemeModel().addTheme(new LayerTheme(layerFactory.createFeatureLayer()));
    mapModel.getViewProperties().setSuggestedWorldBox(new WorldBox(5050, 6900, 8350, 4150));
    anathemaGisView.initGisView(mapModel);
    return anathemaGisView.getComponent();
  }
}