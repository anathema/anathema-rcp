package net.sf.anathema.campaign.plot.repository;

import net.sf.anathema.campaign.plot.PlotPlugin;
import net.sf.anathema.lib.util.IIdentificate;

import org.eclipse.jface.resource.ImageDescriptor;

public class PlotUtils {
  public static ImageDescriptor getImage(IIdentificate unit) {
    return PlotPlugin.getDefaultInstance().getImageDescriptor("icons/Folder" + unit.getId() + "16.png"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
