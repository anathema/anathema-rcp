package net.sf.anathema.character.attributes.model;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;

public class AttributeTemplateProvider implements IAttributeTemplateProvider {

  private static final String EXTENSION_ID = "net.sf.anathema.character.attributes.templates"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TEMPLATE_ID = "characterTemplateId"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORIZATION_COUNT = "favorizationCount"; //$NON-NLS-1$

  public AttributeTemplate getAttributeTemplate(String characterTemplateId) {
    int favorizationCount = getFavorizationCount(characterTemplateId);
    return new AttributeTemplate(favorizationCount);
  }

  private int getFavorizationCount(String characterTemplateId) {
    for (IPluginExtension extension : new EclipseExtensionProvider().getExtensions(EXTENSION_ID)) {
      for (IExtensionElement element : extension.getElements()) {
        String templateId = element.getAttribute(ATTRIB_CHARACTER_TEMPLATE_ID);
        if (templateId.equals(characterTemplateId)) {
          if (element.hasAttribute(ATTRIB_FAVORIZATION_COUNT)) {
            return element.getIntegerAttribute(ATTRIB_FAVORIZATION_COUNT);
          }
          return 0;
        }
      }
    }
    return 0;
  }
}