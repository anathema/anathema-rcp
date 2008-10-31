package net.sf.anathema.map.view.data;

import de.disy.gis.gisterm.map.BasicMapModel;
import de.disy.gis.gisterm.map.IMapModel;

public class GisModel implements IGisModel {

  private final IMapModel mapModel = new BasicMapModel();
  private final StandardLayerFactory standardLayerFactory;

  public GisModel(IGisDataDirectory gisDataDirectory) {
    standardLayerFactory = new StandardLayerFactory(gisDataDirectory);
  }

  public IMapModel getMapModel() {
    return mapModel;
  }

  public IStandardLayerFactory getStandardLayerFactory() {
    return standardLayerFactory;
  }
}