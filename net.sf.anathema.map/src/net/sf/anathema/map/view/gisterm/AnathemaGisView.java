package net.sf.anathema.map.view.gisterm;

import gis.gisterm.gcore.display.algorithm.MapRasterDisplayContext;
import gis.gisterm.map.DisplayToolbar;
import gis.gisterm.map.GisView;
import gis.gisterm.map.IGisView;
import gis.gisterm.map.display.IDisplayContextFactory;
import gis.gisterm.map.locator.LocatorDataManager;
import gis.gisterm.moduleobject.DisplayToolbarActionFactoryRegistry;
import gis.gisterm.moduleobject.IDisplayToolbarActionFactoryProvider;
import gis.services.debug.modules.PlatformlessFeatureLayerBuilderFactory;

import java.awt.BorderLayout;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JPanel;

import de.disy.cadenza.core.extensions.IExtensionProvider;
import de.disy.cadenza.core.extensions.NullExtensionProvider;
import de.disy.gis.gisterm.customization.GisTermCustomizations;
import de.disy.gis.gisterm.file.open.IMapModelThemeAddContext;
import de.disy.gis.gisterm.file.open.NullMapModelThemeAddContext;
import de.disy.gis.gisterm.gfx.display.IMapDisplayContext;
import de.disy.gis.gisterm.map.IMapModel;
import de.disy.gis.gisterm.persistence.IMapPersistence;
import de.disy.gis.gisterm.persistence.MapPersistenceFacade;
import de.disy.gis.gisterm.pro.map.layermanagement.IMapRasterDisplay;

public class AnathemaGisView implements IAnathemaGisView {

  private final JPanel content = new JPanel(new BorderLayout());
  private IGisView gisView;

  public IGisView initGisView(IMapModel mapModel) {
    final IMapPersistence persistenceFacade = MapPersistenceFacade.createPlatformlessFacade();
    final LocatorDataManager locatorDataManager = new LocatorDataManager(
        null,
        persistenceFacade,
        new PlatformlessFeatureLayerBuilderFactory());
    final IMapModelThemeAddContext layerInitializationHandlerRegistry = new NullMapModelThemeAddContext();
    final DisplayToolbar displayToolbar = createDisplayToolbar();
    gisView = new GisView(
        mapModel,
        new NullMapViewConfiguration(),
        locatorDataManager,
        layerInitializationHandlerRegistry,
        new IDisplayContextFactory() {
          @Override
          public IMapDisplayContext create(IMapRasterDisplay display) {
            return new MapRasterDisplayContext(display, displayToolbar);
          }
        },
        new PlatformlessFeatureLayerBuilderFactory(),
        persistenceFacade);
    displayToolbar.connectWith(gisView);
    content.add(displayToolbar, BorderLayout.NORTH);
    content.add(gisView.getContent(), BorderLayout.CENTER);
    return gisView;
  }

  private DisplayToolbar createDisplayToolbar() {
    IDisplayToolbarActionFactoryProvider actionFactoryProvider = new DisplayToolbarActionFactoryRegistry();
    return new DisplayToolbar(new GisTermCustomizations(), actionFactoryProvider);
  }

  public JComponent getComponent() {
    return content;
  }

  public ICoordinateView addCoordinateView(NumberFormat numberFormat) {
    CoordinateView coordView = new CoordinateView(numberFormat);
    content.add(coordView.getComponent(), BorderLayout.SOUTH);
    return coordView;
  }
}