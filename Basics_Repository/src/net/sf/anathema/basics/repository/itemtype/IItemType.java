package net.sf.anathema.basics.repository.itemtype;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.repository.treecontent.itemtype.IItemTypeViewElementFactory;

public interface IItemType {

  public String getProjectName();

  public String getName();

  public String getFileExtension();

  public URL getIconUrl();

  public String getId();

  public String getUntitledName();

  public IItemTypeViewElementFactory getViewElementFactory() throws ExtensionException;
}