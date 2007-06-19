package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.campaign.plot.PlotPlugin;

import org.eclipse.jface.resource.ImageDescriptor;

public class PlotUtils {
  public static ImageDescriptor getImage(PlotUnit unit) {
    return PlotPlugin.getDefaultInstance().getImageDescriptor("icons/Folder" + unit.getPersistenceString() + "16.png"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
