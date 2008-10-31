package net.sf.anathema.basics.repository.treecontent;

import net.sf.anathema.basics.repository.treecontent.itemtype.IViewElement;

import org.eclipse.core.resources.IFile;

public interface IResourceViewElement extends IViewElement {

  public IFile getEditFile();
}