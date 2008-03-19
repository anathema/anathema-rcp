package net.sf.anathema.map.view.gisterm;

import javax.swing.JPopupMenu;

import net.sf.anathema.map.view.exchange.LayerPanelPopupFactory;

import de.disy.cadenza.core.extensions.IExtensionProvider;
import de.disy.cadenza.core.extensions.NullExtensionProvider;
import de.disy.cadenza.core.util.IRegistry;
import de.disy.cadenza.core.util.Registry;
import de.disy.gis.gisterm.map.theme.ITheme;
import de.disy.gis.gisterm.module.FeatureViewActionFactoryRegistry;
import de.disy.gis.gisterm.pro.customization.GisTermProCustomizations;
import de.disy.gis.gisterm.pro.map.layer.ILayerPopupFactory;
import de.disy.gisterm.pro.layer.popup.factory.ISpecializedMenuFactory;
import de.disy.gisterm.pro.map.popupmenu.IMapSelectionContext;
import de.disy.gisterm.pro.menu.IGisTermMenuFactoryProvider;

public class AnathemaLayerPopupFactory implements ILayerPopupFactory {

	@Override
	public JPopupMenu createPopupMenu(ITheme theme,
			IMapSelectionContext selectionContext) {
		final GisTermProCustomizations customizations = new GisTermProCustomizations();
		final IGisTermMenuFactoryProvider menuItemFactoryRegistry = new NullGisTermMenuFactoryProvider();
		final FeatureViewActionFactoryRegistry featureViewActionFactoryRegistry = new FeatureViewActionFactoryRegistry();
		final IRegistry<ISpecializedMenuFactory> specializedMenuFactoryRegistry = new Registry<ISpecializedMenuFactory>();
		final String nullValueDisplayText = "null";
		final IExtensionProvider extensionProvider = new NullExtensionProvider();
		return new LayerPanelPopupFactory(theme, selectionContext.getMapModel(),
				customizations, menuItemFactoryRegistry,
				specializedMenuFactoryRegistry,
				featureViewActionFactoryRegistry, nullValueDisplayText,
				extensionProvider).createPopupMenu(selectionContext);
	}
}