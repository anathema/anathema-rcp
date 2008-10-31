package net.sf.anathema.map.view.data;

import gis.gisterm.gcore.GenericLayer;

public interface IStandardLayerFactory {

  public GenericLayer createXeriarRasterLayer() throws LayerCreationException;

  public GenericLayer createSketchLayer();
}