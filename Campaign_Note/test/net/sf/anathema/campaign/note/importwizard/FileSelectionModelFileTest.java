package net.sf.anathema.campaign.note.importwizard;

import static org.junit.Assert.*;

import java.io.File;

import net.sf.anathema.basics.importwizard.FileSelectionModel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.junit.Before;
import org.junit.Test;

public class FileSelectionModelFileTest {

  private FileSelectionModel model;
  private final String path = "/Campaign_Note/test/net/sf/anathema/campaign/note/importwizard/FileSelectionModelUninitializedTest.java"; //$NON-NLS-1$

  @Before
  public void create() {
    this.model = new FileSelectionModel(new FileSelectionStatusFactory());
    model.setFile(path);
  }

  @Test
  public void isComplete() throws Exception {
    assertTrue(model.isComplete());
  }

  @Test
  public void returnsNeutralMessage() {
    assertEquals(IMessageProvider.NONE, model.getMessage().getMessageType());
  }

  @Test
  public void hasFile() throws Exception {
    assertEquals(new File(path), model.getFile());
  }
}