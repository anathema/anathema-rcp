/**
 * 
 */
package net.sf.anathema.map.view.gisterm;

import gis.gisterm.map.featureview.IFeatureViewActionFactory;
import gis.gisterm.map.featureview.dialog.IAttributeSearchConfiguration;

import java.net.URL;

import net.disy.commons.core.provider.IProvider;
import net.disy.commons.core.provider.StaticProvider;

import de.disy.cadenza.pro.extensions.IExternalBrowserExtension;

public final class AttributeSearchConfiguration implements IAttributeSearchConfiguration {
  @Override
  public String getNullValueDisplayText() {
    return "null";
  }

  @Override
  public IExternalBrowserExtension getExternalBrowser() {
    return new IExternalBrowserExtension() {
    
      @Override
      public void showDocument(URL arg0) {
        // nothing to do
      }
    };
  }

  @Override
  public IProvider<IFeatureViewActionFactory[]> getActionFactoryProvider() {
    return new StaticProvider<IFeatureViewActionFactory[]>(new IFeatureViewActionFactory[0]);
  }
}