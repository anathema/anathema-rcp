package net.sf.anathema.basics.pdfexport;

import static org.easymock.EasyMock.*;

import java.io.File;

import net.sf.anathema.basics.swt.file.IDirectoryPreference;

public class DirectoryPreferenceObjetMother {

  public static IDirectoryPreference create(File directory) {
    IDirectoryPreference preference = createMock(IDirectoryPreference.class);
    expect(preference.getDirectory()).andReturn(directory).anyTimes();
    replay(preference);
    return preference;
  }

}
