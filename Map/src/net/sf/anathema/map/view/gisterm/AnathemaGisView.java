package net.sf.anathema.map.view.gisterm;

import gis.gisterm.map.DisplayToolbar;
import gis.gisterm.map.GisView;
import gis.gisterm.map.IGisView;
import gis.gisterm.map.locator.LocatorDataManager;
import gis.gisterm.moduleobject.DisplayToolbarActionFactoryRegistry;
import gis.gisterm.moduleobject.IDisplayToolbarActionFactoryProvider;

import java.awt.BorderLayout;
import java.text.NumberFormat;

import javax.swing.JComponent;
import javax.swing.JPanel;

import de.disy.cadenza.core.extensions.IExtensionProvider;
import de.disy.cadenza.core.extensions.NullExtensionProvider;
import de.disy.gis.gisterm.customization.GisTermCustomizations;
import de.disy.gis.gisterm.file.open.IMapModelThemeAddContext;
import de.disy.gis.gisterm.file.open.NullMapModelThemeAddContext;
import de.disy.gis.gisterm.map.IMapModel;
import de.disy.gis.gisterm.persistence.IMapPersistence;
import de.disy.gis.gisterm.persistence.MapPersistenceFacade;

public class AnathemaGisView implements IAnathemaGisView {

  private final JPanel content = new JPanel(new BorderLayout());
  private IGisView gisView;

  public IGisView initGisView(IMapModel mapModel) {
    final IMapPersistence persistenceFacade = MapPersistenceFacade.createPlatformlessFacade();
    final IExtensionProvider extensionProvider = new NullExtensionProvider();
    final LocatorDataManager locatorDataManager = new LocatorDataManager(null, persistenceFacade);
    final IMapModelThemeAddContext layerInitializationHandlerRegistry = new NullMapModelThemeAddContext();
    gisView = new GisView(
        mapModel,
        new NullMapViewConfiguration(),
        locatorDataManager,
        extensionProvider,
        layerInitializationHandlerRegistry);
    content.add(createDisplayToolbar(), BorderLayout.NORTH);
    content.add(gisView.getContent(), BorderLayout.CENTER);
    return gisView;
  }

  private DisplayToolbar createDisplayToolbar() {
    IDisplayToolbarActionFactoryProvider actionFactoryProvider = new DisplayToolbarActionFactoryRegistry();
    DisplayToolbar displayToolbar = new DisplayToolbar(new GisTermCustomizations(), actionFactoryProvider );
    displayToolbar.connectWith(gisView);
    return displayToolbar;
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