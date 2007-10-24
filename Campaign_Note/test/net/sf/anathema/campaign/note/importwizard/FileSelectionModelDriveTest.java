package net.sf.anathema.campaign.note.importwizard;

import static org.junit.Assert.*;

import java.io.File;

import net.sf.anathema.basics.importwizard.FileSelectionModel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.junit.Before;
import org.junit.Test;

public class FileSelectionModelDriveTest {

  private FileSelectionModel model;

  @Before
  public void create() {
    this.model = new FileSelectionModel(new FileSelectionStatusFactory());
    model.setFile("C:"); //$NON-NLS-1$
  }

  @Test
  public void isNotComplete() throws Exception {
    assertFalse(model.isComplete());
  }

  @Test
  public void returnsErrorMessage() {
    assertEquals(IMessageProvider.ERROR, model.getMessage().getMessageType());
  }

  @Test
  public void hasRootDirectory() throws Exception {
    assertEquals(new File("C:"), model.getFile()); //$NON-NLS-1$
  }
}