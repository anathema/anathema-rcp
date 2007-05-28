package net.sf.anathema.campaign.note;

import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

public class NotesRepositoryUtilities {

  public static IItemType getNotesItemType() {
    return new ItemTypeProvider().getById("net.sf.anathema.itemtype.Note"); //$NON-NLS-1$
  }
}