package net.sf.anathema.character.trait.model;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

public class TraitTemplateFactory implements ITraitTemplateFactory {

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
  public ITraitTemplate getTraitTemplate(String traitId) {
    IExtensionElement traitElement = getExtensionElement(traitId);
    if (traitElement == null) {
      return createDefaultTemplate();
    }
    return createTemplate(traitElement.getIntegerAttribute("minimalValue")); //$NON-NLS-1$
  }

  private IExtensionElement getExtensionElement(String traitId) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        if (element.getAttribute("characterTemplateId").equals(templateId)) { //$NON-NLS-1$
          for (IExtensionElement child : element.getElements()) {
            if (child.getAttribute("traitId").equals(traitId)) { //$NON-NLS-1$
              return child;
            }
          }
        }
      }
    }
    return null;
  }

  private ITraitTemplate createDefaultTemplate() {
    return createTemplate(minimalValue);
  }

  private ITraitTemplate createTemplate(int minValue) {
    EssenceSensitiveTraitTemplate traitTemplate = new EssenceSensitiveTraitTemplate();
    traitTemplate.setMiniumalValue(minValue);
    return traitTemplate;
  }
}