package net.sf.anathema.basics.jface.context;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchPartSite;

public class ContextMenuManager {

  public static void connect(IWorkbenchPartSite site, Viewer viewer) {
      MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
      menuMgr.setRemoveAllWhenShown(true);
      Menu menu = menuMgr.createContextMenu(viewer.getControl());
      viewer.getControl().setMenu(menu);
      site.registerContextMenu(menuMgr, viewer);
  }
}