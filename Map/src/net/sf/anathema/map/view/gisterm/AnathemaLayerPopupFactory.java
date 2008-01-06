package net.sf.anathema.map.view.gisterm;

import javax.swing.JPopupMenu;

import de.disy.gis.gisterm.map.theme.ITheme;
import de.disy.gis.gisterm.pro.map.layer.ILayerPopupFactory;
import de.disy.gisterm.pro.map.popupmenu.IMapSelectionContext;

public class AnathemaLayerPopupFactory implements ILayerPopupFactory {

  @Override
  public JPopupMenu createPopupMenu(ITheme theme, IMapSelectionContext selectionContext) {
    return null;
  }
}