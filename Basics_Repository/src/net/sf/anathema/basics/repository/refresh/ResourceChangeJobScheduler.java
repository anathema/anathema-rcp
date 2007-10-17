package net.sf.anathema.basics.repository.refresh;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.Job;

public class ResourceChangeJobScheduler implements IDisposable {

  private final IResourceChangeListener resourceListener = new IResourceChangeListener() {
    @Override
    public void resourceChanged(IResourceChangeEvent event) {
      refresher.schedule();
    }
  };
  private final Job refresher;

  public ResourceChangeJobScheduler(Job refresher) {
    this.refresher = refresher;
    refresher.setRule(ResourcesPlugin.getWorkspace().getRoot());
    ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceListener);
  }

  @Override
  public void dispose() {
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceListener);
  }
}