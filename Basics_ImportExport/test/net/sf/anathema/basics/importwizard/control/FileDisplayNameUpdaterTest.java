package net.sf.anathema.basics.importwizard.control;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.File;

import net.sf.anathema.basics.importwizard.IFileProvider;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.Before;
import org.junit.Test;

public class FileDisplayNameUpdaterTest {

  private Text widget;

  @Before
  public void createTextWidget() {
    Shell shell = new Shell();
    widget = new Text(shell, SWT.NONE);
    widget.setText("Hasä"); //$NON-NLS-1$
  }

  @Test
  public void updatesTextToEmptyTextWithNoFile() throws Exception {
    IFileProvider fileProvider = createNiceMock(IFileProvider.class);
    replay(fileProvider);
    new FileDisplayNameUpdater(widget, fileProvider).stateChanged();
    assertEquals("", widget.getText()); //$NON-NLS-1$
  }

  @Test
  public void updatesTextToAbsoluteFileNameForFile() throws Exception {
    File file = new File("C:/Has/Tum"); //$NON-NLS-1$
    IFileProvider fileProvider = createNiceMock(IFileProvider.class);
    expect(fileProvider.getFile()).andReturn(file);
    replay(fileProvider);
    new FileDisplayNameUpdater(widget, fileProvider).stateChanged();
    assertEquals(file.getAbsolutePath(), widget.getText());
  }
}