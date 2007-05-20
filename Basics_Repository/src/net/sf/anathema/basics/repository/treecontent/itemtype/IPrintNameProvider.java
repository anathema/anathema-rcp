package net.sf.anathema.basics.repository.treecontent.itemtype;

import org.eclipse.core.resources.IFile;

public interface IPrintNameProvider {

  public String getPrintName(IFile file);
}