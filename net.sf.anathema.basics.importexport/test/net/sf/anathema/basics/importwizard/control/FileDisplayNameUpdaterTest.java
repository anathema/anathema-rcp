package net.sf.anathema.basics.importwizard.control;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.File;

import net.sf.anathema.basics.importexport.IFileProvider;
import net.sf.anathema.basics.importexport.control.FileDisplayNameUpdater;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("nls")
public class FileDisplayNameUpdaterTest {

  private Text widget;

  @Before
  public void createTextWidget() {
    Shell shell = new Shell();
    widget = new Text(shell, SWT.NONE);
    widget.setText("Hasä");
  }

  @Test
  public void updatesTextToEmptyTextWithNoFile() throws Exception {
    IFileProvider fileProvider = createNiceMock(IFileProvider.class);
    replay(fileProvider);
    new FileDisplayNameUpdater(widget, fileProvider).stateChanged();
    assertEquals("", widget.getText());
  }

  @Test
  public void updatesTextToAbsoluteFileNameForFile() throws Exception {
    File file = new File("C:/Has/Tum");
    IFileProvider fileProvider = createNiceMock(IFileProvider.class);
    expect(fileProvider.getFile()).andStubReturn(file);
    replay(fileProvider);
    new FileDisplayNameUpdater(widget, fileProvider).stateChanged();
    assertEquals(file.getAbsolutePath(), widget.getText());
  }
}