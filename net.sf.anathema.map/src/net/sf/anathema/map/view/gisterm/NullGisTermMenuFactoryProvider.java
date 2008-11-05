package net.sf.anathema.map.view.gisterm;

import de.disy.gisterm.pro.map.popupmenu.IMapMenuItemFactory;
import de.disy.gisterm.pro.menu.IGisTermMenuFactoryProvider;

public class NullGisTermMenuFactoryProvider implements IGisTermMenuFactoryProvider {

  @Override
  public IMapMenuItemFactory[] getLegendContextMenuItemFactories() {
    // Hier kommen wir tatsächlich vorbei bei Rechtsklick auf Legendeneintrag
    return new IMapMenuItemFactory[0];
  }

  @Override
  public IMapMenuItemFactory[] getMapContextMenuItemFactories() {
    // Hier kommen wir bei Erzeugen des Kontextmenüs der Karte nicht vorbei
    return new IMapMenuItemFactory[0];
  }
}