package net.sf.anathema.map.view.data;

import de.disy.gis.gisterm.map.IMapModel;

public interface IGisModel {

  public IMapModel getMapModel();

  public IStandardLayerFactory getStandardLayerFactory();
}