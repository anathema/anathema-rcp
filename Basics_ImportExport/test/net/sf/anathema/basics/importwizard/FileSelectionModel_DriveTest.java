package net.sf.anathema.basics.importwizard;

import static org.junit.Assert.*;

import java.io.File;

import net.sf.anathema.basics.importexport.FileSelectionModel;

import org.junit.Before;
import org.junit.Test;

public class FileSelectionModel_DriveTest {

  private FileSelectionModel model;

  @Before
  public void create() {
    this.model = new FileSelectionModel(new DummyStatusFactory());
    model.setFile("C:"); //$NON-NLS-1$
  }

  @Test
  public void isNotComplete() throws Exception {
    assertFalse(model.isComplete());
  }

  @Test
  public void hasRootDirectory() throws Exception {
    assertEquals(new File("C:"), model.getFile()); //$NON-NLS-1$
  }
}