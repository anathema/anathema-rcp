package net.sf.anathema.basics.repository.refresh;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

public class ResourceChangeTreeRefresher implements IDisposable {

  private final IResourceChangeListener resourceListener = new IResourceChangeListener() {
    @Override
    public void resourceChanged(IResourceChangeEvent event) {
      treeViewRefresher.schedule();
    }
  };
  private final TreeViewRefresher treeViewRefresher;

  public ResourceChangeTreeRefresher(TreeViewer viewer, Display display) {
    treeViewRefresher = new TreeViewRefresher(viewer, display);
    treeViewRefresher.setRule(ResourcesPlugin.getWorkspace().getRoot());
    ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
  }

  @Override
  public void dispose() {
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceListener);
  }
}