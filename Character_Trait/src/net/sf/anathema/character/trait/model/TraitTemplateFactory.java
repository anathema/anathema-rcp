package net.sf.anathema.character.trait.model;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

public class TraitTemplateFactory implements IMinimalValueFactory {

  private static final String POINT_ID = "collectionTemplates"; //$NON-NLS-1$
  private final int minimalValue;
  private final IPluginExtension[] extensions;
  private final String templateId;

  public TraitTemplateFactory(int minimalValue, String templateId) {
    this(minimalValue, templateId, new EclipseExtensionPoint(CharacterTraitPlugin.PLUGIN_ID, POINT_ID).getExtensions());
  }

  public TraitTemplateFactory(int minimalValue, String templateId, IPluginExtension... extensions) {
    this.templateId = templateId;
    this.minimalValue = minimalValue;
    this.extensions = extensions;
  }

  @Override
  public int getMinimalValue(String traitId) {
    IExtensionElement traitElement = getExtensionElement(traitId);
    if (traitElement == null) {
      return minimalValue;
    }
    return traitElement.getIntegerAttribute("value"); //$NON-NLS-1$
  }

  private IExtensionElement getExtensionElement(String traitId) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        if (element.getAttribute("characterTemplateId").equals(templateId)) { //$NON-NLS-1$
          for (IExtensionElement child : element.getElements("minimalValue")) { //$NON-NLS-1$
            if (child.getAttribute("traitId").equals(traitId)) { //$NON-NLS-1$
              return child;
            }
          }
        }
      }
    }
    return null;
  }
}