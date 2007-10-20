package net.sf.anathema.character.points.problems;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.points.plugin.PointPluginConstants;

public class BonusPointExtensionPoint {

  private static final String ATTRIB_AMOUNT = "amount"; //$NON-NLS-1$
  private static final String ATTRIB_TEMPLATE_ID = "templateId"; //$NON-NLS-1$
  private final IPluginExtension[] extensions;

  public BonusPointExtensionPoint() {
    this(new EclipseExtensionPoint(PointPluginConstants.PLUGIN_ID, "bonuspoints").getExtensions()); //$NON-NLS-1$
  }

  public BonusPointExtensionPoint(IPluginExtension... extensions) {
    this.extensions = extensions;
  }

  public int getBonusPoints(String templateId) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        if (templateId.equals(element.getAttribute(ATTRIB_TEMPLATE_ID))) {
          return element.getIntegerAttribute(ATTRIB_AMOUNT);
        }
      }
    }
    return 0;
  }
}