package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.swt.graphics.Image;

public interface IViewElement {

  boolean hasChildren();

  Object[] getChildren();

  Object getParent();

  String getDisplayName();

  Image getImage();

}
