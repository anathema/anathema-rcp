package net.sf.anathema.campaign.note.report;

import static org.junit.Assert.*;
import net.sf.anathema.basics.item.text.ITitledText;
import net.sf.anathema.basics.item.text.TitledText;

import org.junit.Before;
import org.junit.Test;

public class NoteExportItemTest {

  private NoteExportItem item;

  @Before
  public void createExportItemWithEmptyText() {
    item = new NoteExportItem(new TitledText());
  }

  @Test
  public void equalsForEqualItems() throws Exception {
    assertEquals(item, new NoteExportItem(new TitledText()));
  }

  @Test
  public void doesNotEqualObject() throws Exception {
    assertFalse(item.equals(new Object()));
  }

  @Test
  public void doesNotEqualNoteObjectWithNullText() throws Exception {
    assertFalse(item.equals(new NoteExportItem((ITitledText) null)));
  }

  @Test
  public void hasSameHashCodeAsExportItemWithEmptyText() throws Exception {
    assertEquals(item.hashCode(), new NoteExportItem(new TitledText()).hashCode());
  }
}