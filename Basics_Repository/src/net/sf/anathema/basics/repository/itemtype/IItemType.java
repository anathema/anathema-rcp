package net.sf.anathema.basics.repository.itemtype;

import java.net.URL;

import org.eclipse.core.resources.IResource;

public interface IItemType {

  public String getProjectName();

  public String getName();

  public String getFileExtension();

  public URL getIconUrl();

  public String getId();

  public String getUntitledName();

  public boolean supports(IResource resource);
}