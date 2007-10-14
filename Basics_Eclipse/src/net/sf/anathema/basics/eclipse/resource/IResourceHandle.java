package net.sf.anathema.basics.eclipse.resource;

import org.eclipse.core.runtime.IAdaptable;

public interface IResourceHandle extends IAdaptable {

  public boolean exists();
}