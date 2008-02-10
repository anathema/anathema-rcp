package net.sf.anathema.basics.importwizard;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import net.sf.anathema.basics.importexport.FileSelectionModel;

import org.junit.Before;
import org.junit.Test;

public class FileSelectionModelFileTest {

  private FileSelectionModel model;
  private String path;

  @Before
  public void create() throws IOException {
    model = new FileSelectionModel(new DummyStatusFactory());
    File file = File.createTempFile("horst", "hugo"); //$NON-NLS-1$ //$NON-NLS-2$
    path = file.getPath();
    model.setFile(path);
  }

  @Test
  public void isComplete() throws Exception {
    assertTrue(model.isComplete());
  }

  @Test
  public void hasFile() throws Exception {
    assertEquals(new File(path), model.getFile());
  }
}