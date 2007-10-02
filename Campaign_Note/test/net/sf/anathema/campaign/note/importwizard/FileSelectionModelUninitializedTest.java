package net.sf.anathema.campaign.note.importwizard;

import static org.junit.Assert.*;
import net.sf.anathema.campaign.core.importwizard.FileSelectionModel;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.junit.Before;
import org.junit.Test;

public class FileSelectionModelUninitializedTest {

  private FileSelectionModel model;

  @Before
  public void create() {
    this.model = new FileSelectionModel(new FileSelectionStatusFactory());
  }

  @Test
  public void isNotComplete() throws Exception {
    assertFalse(model.isComplete());
  }

  @Test
  public void returnsNeutralMessage() {
    assertEquals(IMessageProvider.NONE, model.getMessage().getMessageType());
  }

  @Test
  public void hasNoFile() throws Exception {
    assertNull(model.getFile());
  }
}