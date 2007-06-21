package net.sf.anathema.campaign.plot.repository;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.resource.ResourceUtils;
import net.sf.anathema.lib.util.Identificate;

public class ExtensionPlotUnit extends Identificate implements IPlotUnit {

  private final IExtensionElement element;
  private IPlotUnit successor;

  public ExtensionPlotUnit(IExtensionElement element) {
    super(element.getAttribute("id")); //$NON-NLS-1$
    this.element = element;
  }

  @Override
  public String getName() {
    return element.getAttribute("name"); //$NON-NLS-1$
  }

  @Override
  public IPlotUnit getSuccessor() {
    return successor;
  }

  public URL getImage() {
    String iconPath = element.getAttribute("icon"); //$NON-NLS-1$
    return ResourceUtils.getResourceUrl("net.sf.anathema.campaign.plot", iconPath);
  }

  public void setSuccessor(IPlotUnit unit) {
    this.successor = unit;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ExtensionPlotUnit)) {
      return false;
    }
    return super.equals(obj);
  }
}