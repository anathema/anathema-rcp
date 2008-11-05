package net.sf.anathema.map.view.gisterm;

import gis.gisterm.PanVisualizationType;
import de.disy.gis.gisterm.file.extension.IGisFileTypeProvider;
import de.disy.gis.gisterm.file.extension.NullGisFileTypeProvider;
import de.disy.gis.gisterm.map.theme.IThemeClosableValidator;
import de.disy.gisterm.pro.map.popupmenu.IMenuItemFactoryRegistry;
import de.disy.gisterm.pro.map.view.configuration.IMapViewConfiguration;
import de.disy.lib.image.ImageResolution;

public class NullMapViewConfiguration implements IMapViewConfiguration {

  private IMenuItemFactoryRegistry menuItemFactory;

  public boolean isDemo() {
    return false;
  }

  public IMenuItemFactoryRegistry getContextMenuItemFactoryRegistry() {
    return menuItemFactory;
  }

  public ImageResolution getScreenResolution() {
    return ImageResolution.SCREEN;
  }

  public IGisFileTypeProvider getGisFileProvider() {
    return NullGisFileTypeProvider.INSTANCE;
  }

  @Override
  public boolean isBorderNavigationEnabled() {
    return true;
  }

  @Override
  public PanVisualizationType isRestrictedPanVisualization() {
    return PanVisualizationType.FULL;
  }

  @Override
  public boolean isAnimatedNavigationEnabled() {
    return false;
  }

  @Override
  public IThemeClosableValidator getThemeClosableValidator() {
    return new NullClosableValidator();
  }
}