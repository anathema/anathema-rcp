/**
 * 
 */
package net.sf.anathema.map.view.gisterm;

import de.disy.gis.gisterm.map.theme.IThemeClosableValidator;
import de.disy.gisterm.pro.layer.popup.ILayerPopupConfiguration;

public final class SimpleLayerPopupConfiguration implements ILayerPopupConfiguration {
  @Override
  public IThemeClosableValidator getThemeClosableValidator() {
    return new NullClosableValidator();
  }

  @Override
  public boolean isSaveToFileSystem() {
    return false;
  }

  @Override
  public boolean isSaveToRepository() {
    return false;
  }

  @Override
  public boolean isThemeGroupingActionsEnabled() {
    return false;
  }
}