package net.sf.anathema.map.view.gisterm;

import gis.gisterm.map.featureview.dialog.IAttributeSearchConfiguration;

import javax.swing.JPopupMenu;

import de.disy.cadenza.core.util.IRegistry;
import de.disy.cadenza.core.util.Registry;
import de.disy.gis.gisterm.map.theme.ITheme;
import de.disy.gis.gisterm.pro.map.layer.ILayerPopupFactory;
import de.disy.gisterm.pro.layer.popup.ILayerPopupConfiguration;
import de.disy.gisterm.pro.layer.popup.LayerPanelPopupFactory;
import de.disy.gisterm.pro.layer.popup.factory.ISpecializedMenuFactory;
import de.disy.gisterm.pro.map.popupmenu.IMapSelectionContext;
import de.disy.gisterm.pro.menu.IGisTermMenuFactoryProvider;

public class AnathemaLayerPopupFactory implements ILayerPopupFactory {

  @Override
  public JPopupMenu createPopupMenu(ITheme theme, IMapSelectionContext selectionContext) {
    final IGisTermMenuFactoryProvider menuItemFactoryRegistry = new NullGisTermMenuFactoryProvider();
    final IRegistry<ISpecializedMenuFactory> specializedMenuFactoryRegistry = new Registry<ISpecializedMenuFactory>();
    ILayerPopupConfiguration popupConfiguration = new SimpleLayerPopupConfiguration();
    IAttributeSearchConfiguration searchConfiguration = new AttributeSearchConfiguration();
    return new LayerPanelPopupFactory(
        theme,
        popupConfiguration,
        menuItemFactoryRegistry,
        specializedMenuFactoryRegistry,
        searchConfiguration).createPopupMenu(selectionContext);
  }
}