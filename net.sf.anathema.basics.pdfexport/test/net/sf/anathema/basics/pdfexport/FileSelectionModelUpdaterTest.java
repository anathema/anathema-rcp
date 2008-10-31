package net.sf.anathema.basics.pdfexport;

import static org.easymock.EasyMock.*;

import java.io.File;

import net.sf.anathema.basics.eclipse.runtime.IProvider;
import net.sf.anathema.basics.importexport.IFileSelectionModel;
import net.sf.anathema.basics.swt.file.IDirectoryPreference;

import org.junit.Before;
import org.junit.Test;

public class FileSelectionModelUpdaterTest {

  private static final File DIRECTORY = new File("."); //$NON-NLS-1$
  private FileSelectionModelUpdater<Object> updater;
  private IFileSelectionModel selectionModel;
  private IProvider<String> nameProvider;

  @SuppressWarnings("unchecked")
  @Before
  public void createUpdater() {
    IDirectoryPreference preference = DirectoryPreferenceObjetMother.create(DIRECTORY);
    selectionModel = createMock(IFileSelectionModel.class);
    nameProvider = createMock(IProvider.class);
    updater = new FileSelectionModelUpdater<Object>(preference, selectionModel, nameProvider);
  }

  @Test
  public void deselectsFileOnStateChangeForUnknownFileName() throws Exception {
    expect(nameProvider.get()).andReturn(null).anyTimes();
    replay(nameProvider);
    selectionModel.setFile((File) null);
    replay(selectionModel);
    updater.stateChanged();
    verify(selectionModel);
  }

  @Test
  public void selectsDirectoryCorrectlyNamedChildOnStateChangeForKnownFileName() throws Exception {
    expect(nameProvider.get()).andReturn("filename").anyTimes(); //$NON-NLS-1$
    replay(nameProvider);
    selectionModel.setFile(new File(DIRECTORY, "filename.pdf")); //$NON-NLS-1$
    replay(selectionModel);
    updater.stateChanged();
    verify(selectionModel);
  }
}