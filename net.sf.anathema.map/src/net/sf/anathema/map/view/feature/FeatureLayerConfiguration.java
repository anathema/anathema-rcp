/**
 * 
 */
package net.sf.anathema.map.view.feature;

import gis.gisterm.map.featurelayer.configuration.IFeatureLayerConfiguration;

public final class FeatureLayerConfiguration implements IFeatureLayerConfiguration {
  public String getNullValueDisplayText() {
    return "Nix";
  }

  public boolean isScaleLabelsByDefaultEnabled() {
    return false;
  }
}