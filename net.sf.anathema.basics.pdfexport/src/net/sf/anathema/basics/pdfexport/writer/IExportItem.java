package net.sf.anathema.basics.pdfexport.writer;

import org.eclipse.core.resources.IResource;

public interface IExportItem<T> {

  public T createItem();

  public String getPrintName();

  public boolean isFor(IResource resource);
}