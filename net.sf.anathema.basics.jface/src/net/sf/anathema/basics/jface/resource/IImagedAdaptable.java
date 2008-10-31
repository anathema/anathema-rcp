package net.sf.anathema.basics.jface.resource;

import java.net.URL;

import org.eclipse.core.runtime.IAdaptable;

public interface IImagedAdaptable extends IAdaptable {

  public URL getImageUrl();
}