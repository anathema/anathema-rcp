package net.sf.anathema.map.view.gisterm;

import de.disy.gisterm.pro.map.popupmenu.IMapMenuItemFactory;
import de.disy.gisterm.pro.menu.IGisTermMenuFactoryProvider;

public class NullGisTermMenuFactoryProvider implements
		IGisTermMenuFactoryProvider {

	@Override
	public IMapMenuItemFactory[] getLegendContextMenuItemFactories() {
		return new IMapMenuItemFactory[0];
	}

	@Override
	public IMapMenuItemFactory[] getMapContextMenuItemFactories() {
		return new IMapMenuItemFactory[0];
	}
}
