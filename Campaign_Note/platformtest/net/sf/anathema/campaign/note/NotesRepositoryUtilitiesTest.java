package net.sf.anathema.campaign.note;

import static org.junit.Assert.*;

import org.junit.Test;

public class NotesRepositoryUtilitiesTest {

  @Test
  public void doesNotFindAnyNotesFileInEmptyRepository() throws Exception {
    assertTrue(NotesRepositoryUtilities.getNoteFiles().isEmpty());
  }
}