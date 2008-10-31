package net.sf.anathema.basics.eclipse.resource;

import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;

public final class ResourceChangeListenerDisposable implements IDisposable {
  private final IResourceChangeListener resourceListener;

  public ResourceChangeListenerDisposable(IResourceChangeListener resourceListener) {
    this.resourceListener = resourceListener;
  }

  @Override
  public void dispose() {
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceListener);
  }
}