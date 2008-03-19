package net.sf.anathema.map.view.feature;

import gis.gisterm.dialog.maplayer.IMapLayerPropertiesDialog;
import gis.gisterm.map.FeatureLayer;
import gis.gisterm.map.FeatureProxySet;
import gis.gisterm.map.layer.datasource.FeatureLayerDataProvider;
import gis.gisterm.map.legend.StandardLegendFactory;
import net.sf.anathema.map.view.data.AnathemaLayerCustomizer;

public class AnathemaFeatureLayer extends FeatureLayer {

  public AnathemaFeatureLayer() {
    super(
        new FeatureProxySet(),
        "Platformless Feature Layer",
        StandardLegendFactory.createLegendLayer(null),
        new FeatureLayerDataProvider(),
        new FeatureLayerConfiguration(),
        new LayerAttributeList());
  }

  @Override
  protected void customizePropertiesDialog(IMapLayerPropertiesDialog dialog) {
    AnathemaLayerCustomizer.customizePropertiesDialog(dialog, this);
  }
}
