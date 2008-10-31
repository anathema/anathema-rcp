package net.sf.anathema.campaign.plot.creation;

import java.util.List;

import net.sf.anathema.basics.repository.access.RepositoryUtilities;
import net.sf.anathema.basics.repository.itemtype.IItemType;
import net.sf.anathema.basics.repository.itemtype.ItemTypeProvider;

import org.eclipse.core.resources.IFolder;

public class PlotRepositoryUtilities {

  public static IItemType getPlotItemType() {
    return new ItemTypeProvider().getById("net.sf.anathema.itemtype.Plot"); //$NON-NLS-1$
  }

  public static List<IFolder> getAllPlotFolders() {
    return RepositoryUtilities.getItemFolders(getPlotItemType());
  }
}