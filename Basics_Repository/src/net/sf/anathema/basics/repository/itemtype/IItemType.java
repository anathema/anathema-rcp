package net.sf.anathema.basics.repository.itemtype;

import java.net.URL;

public interface IItemType {

  public String getProjectName();

  public String getName();

  public String getFileExtension();

  public URL getIconUrl();

  public String getId();

  public String getUntitledName();
}