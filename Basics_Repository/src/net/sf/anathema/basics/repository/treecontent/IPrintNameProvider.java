package net.sf.anathema.basics.repository.treecontent;

import org.eclipse.core.resources.IFile;

public interface IPrintNameProvider {

  public String getPrintName(IFile file);
}