package net.sf.anathema.basics.importwizard;

import static org.junit.Assert.*;
import net.sf.anathema.basics.importexport.FileSelectionModel;

import org.junit.Before;
import org.junit.Test;

public class FileSelectionModel_UninitializedTest {

  private FileSelectionModel model;

  @Before
  public void create() {
    model = new FileSelectionModel(new DummyStatusFactory());
  }

  @Test
  public void isNotComplete() throws Exception {
    assertFalse(model.isComplete());
  }

  @Test
  public void hasNoFile() throws Exception {
    assertNull(model.getFile());
  }
}