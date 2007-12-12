package net.sf.anathema.map.view.gisterm;

import java.awt.Component;

import javax.swing.JPopupMenu;

import net.disy.commons.swing.action.SmartAction;
import de.disy.gis.gisterm.map.theme.ITheme;
import de.disy.gis.gisterm.pro.map.layer.ILayerPopupFactory;
import de.disy.gisterm.pro.map.popupmenu.IMapSelectionContext;

public class AnathemaLayerPopupFactory implements ILayerPopupFactory {

  @Override
  public JPopupMenu createPopupMenu(ITheme theme, IMapSelectionContext selectionContext) {
    JPopupMenu popupMenu = new JPopupMenu("Ich bin ein Popup"); //$NON-NLS-1$
    popupMenu.add(new SmartAction("Ohh yess") {
      @Override
      protected void execute(Component parent) {
        // TODO Auto-generated method stub
      }
    });
    return popupMenu;
  }
}