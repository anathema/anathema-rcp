package net.sf.anathema.campaign.plot.repository;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.campaign.plot.PlotPlugin;

public class PlotUnitProvider {

  private static final String UNITS_EXTENSION_POINT = "units"; //$NON-NLS-1$
  private IPlotUnit root;

  public PlotUnitProvider() {
    Map<String, ExtensionPlotUnit> units = new HashMap<String, ExtensionPlotUnit>();
    for (IPluginExtension extension : new EclipseExtensionPoint(PlotPlugin.ID, UNITS_EXTENSION_POINT).getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        ExtensionPlotUnit unit = new ExtensionPlotUnit(extension.getContributorId(), element);
        String parentId = element.getAttribute("parentId"); //$NON-NLS-1$
        if (parentId == null) {
          Ensure.ensureArgumentNull("Only one root unit may be defined.", root); //$NON-NLS-1$
          this.root = unit;
        }
        else {
          units.get(parentId).setSuccessor(unit);
        }
        if (element.getBooleanAttribute("recursive")) { //$NON-NLS-1$
          unit.setSuccessor(unit);
        }
        else {
          units.put(element.getAttribute("id"), unit); //$NON-NLS-1$
        }
      }
    }
  }

  public IPlotUnit getRootUnit() {
    return root;
  }
}