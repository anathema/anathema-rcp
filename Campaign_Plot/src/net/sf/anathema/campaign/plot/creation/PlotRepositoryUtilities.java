package net.sf.anathema.campaign.plot.creation;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

import org.eclipse.core.resources.IProject;

public class PlotRepositoryUtilities {

  public static IItemType getSeriesItemType() {
    return new ItemTypeProvider().getById("net.sf.anathema.itemtype.Plot"); //$NON-NLS-1$
  }

  public static IProject getNotesProject() {
    IItemType notesItemType = getSeriesItemType();
    return RepositoryUtilities.getProject(notesItemType);
  }
}