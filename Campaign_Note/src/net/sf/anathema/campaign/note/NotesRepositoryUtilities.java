package net.sf.anathema.campaign.note;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;
import net.sf.anathema.campaign.note.plugin.NotePluginConstants;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

public class NotesRepositoryUtilities {

  public static final Logger logger = new Logger(NotePluginConstants.PLUGIN_ID);
  
  public static IItemType getNotesItemType() {
    return new ItemTypeProvider().getById("net.sf.anathema.itemtype.Note"); //$NON-NLS-1$
  }
  
  public static List<IFile> getNoteFiles() {
    List<IFile> noteFiles = new ArrayList<IFile>();
    try {
      for (IResource resource : RepositoryUtilities.getProject(getNotesItemType()).members()) {
        if (resource instanceof IFile) {
          noteFiles.add((IFile) resource);
        }
      }
    }
    catch (CoreException e) {
      logger.error("Error retrieving members of notes folder.", e);
    }
    return noteFiles;
  }
}