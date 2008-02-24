package net.sf.anathema.basics.jface.context;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPartSite;

public class ContextMenuManager {

  public static void connect(IWorkbenchPartSite site, Viewer viewer) {
    final MenuManager manager = new MenuManager("#PopupMenu"); //$NON-NLS-1$
    manager.setRemoveAllWhenShown(true);
    manager.addMenuListener(new IMenuListener() {
      @Override
      public void menuAboutToShow(IMenuManager menuManager) {
        menuManager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
      }
    });
    Menu menu = manager.createContextMenu(viewer.getControl());
    viewer.getControl().setMenu(menu);
    site.registerContextMenu(manager, viewer);
  }
}