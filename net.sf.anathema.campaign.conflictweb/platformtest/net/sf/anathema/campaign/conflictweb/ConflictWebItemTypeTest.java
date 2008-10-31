package net.sf.anathema.campaign.conflictweb;

import static org.junit.Assert.*;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

import org.junit.Before;
import org.junit.Test;

public class ConflictWebItemTypeTest {

  private IItemType itemType;

  @Before
  public void findItemType() {
    itemType = new ItemTypeProvider().getById("net.sf.anathema.itemtype.ConflictWeb"); //$NON-NLS-1$
  }

  @Test
  public void conflictWebItemTypeIsRegistered() throws Exception {
    assertNotNull(itemType);
  }

  @Test
  public void hasConflictFileExtension() throws Exception {
    assertEquals(ConflictWebIntegrationConstants.CONFLICT_WEB_FILE_EXTENSION, itemType.getFileExtension());
  }

  @Test
  public void hasProjectNameConflictWeb() throws Exception {
    assertEquals("ConflictWeb", itemType.getProjectName()); //$NON-NLS-1$
  }
}