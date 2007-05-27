package net.sf.anathema.campaign.note;

import org.eclipse.core.resources.IProject;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

public class NotesRepositoryUtilities {

  public static IItemType getNotesItemType() {
    return new ItemTypeProvider().getById("net.sf.anathema.itemtype.Note"); //$NON-NLS-1$
  }

  public static IProject getNotesProject() {
    IItemType notesItemType = getNotesItemType();
    return RepositoryUtilities.getProject(notesItemType);
  }

}
