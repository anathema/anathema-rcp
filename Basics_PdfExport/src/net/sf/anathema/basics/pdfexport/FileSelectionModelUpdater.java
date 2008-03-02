package net.sf.anathema.basics.pdfexport;

import java.io.File;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.importexport.IFileSelectionModel;
import net.sf.anathema.basics.swt.file.IDirectoryPreference;

public final class FileSelectionModelUpdater<I> implements IChangeListener {
  private final IProvider<String> fileNameProvider;
  private final IFileSelectionModel fileSelectionModel;
  private final IDirectoryPreference directoryPreference;

  public FileSelectionModelUpdater(
      IDirectoryPreference directoryPreference,
      IFileSelectionModel fileSelectionModel,
      IProvider<String> fileNameProvider) {
    this.directoryPreference = directoryPreference;
    this.fileSelectionModel = fileSelectionModel;
    this.fileNameProvider = fileNameProvider;
  }

  @Override
  public void stateChanged() {
    String fileName = fileNameProvider.get();
    if (fileName == null) {
      fileSelectionModel.setFile((File) null);
    }
    else {
      File directory = directoryPreference.getDirectory();
      fileSelectionModel.setFile(new File(directory, fileName + ".pdf")); //$NON-NLS-1$
    }
  }
}