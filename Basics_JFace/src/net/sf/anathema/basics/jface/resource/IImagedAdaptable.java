package net.sf.anathema.basics.jface.resource;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;

public interface IImagedAdaptable extends IAdaptable {

  public ImageDescriptor getImageDescriptor();
}