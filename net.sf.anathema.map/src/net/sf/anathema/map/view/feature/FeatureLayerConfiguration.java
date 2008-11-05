/**
 * 
 */
package net.sf.anathema.map.view.feature;

import java.util.HashMap;

import gis.gisterm.map.featurelayer.configuration.IFeatureLayerConfiguration;

public final class FeatureLayerConfiguration implements IFeatureLayerConfiguration {
  public String getNullValueDisplayText() {
    return "Nix";
  }

  public boolean isScaleLabelsByDefaultEnabled() {
    return false;
  }

  @Override
  public HashMap<String, String> getDefaultAttributePrintnames() {
    return new HashMap<String, String>();
  }
}