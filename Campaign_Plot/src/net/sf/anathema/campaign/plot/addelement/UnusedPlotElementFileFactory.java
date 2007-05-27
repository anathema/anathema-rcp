package net.sf.anathema.campaign.plot.addelement;

import net.sf.anathema.basics.repository.input.AbstractUnusedFileFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;

public class UnusedPlotElementFileFactory extends AbstractUnusedFileFactory<IFolder> {

  public UnusedPlotElementFileFactory(IFolder container, String fileExtension) {
    super(container, fileExtension);
  }

  @Override
  protected IFile createFile(String fileName) {
    return getContainer().getFile(fileName);
  }
}