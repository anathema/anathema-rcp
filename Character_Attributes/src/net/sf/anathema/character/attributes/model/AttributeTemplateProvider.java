package net.sf.anathema.character.attributes.model;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.attributes.AttributesPlugin;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;

public class AttributeTemplateProvider implements ITraitCollectionTemplateProvider {

  private static final String TEMPLATES_EXTENSION_POINT = "templates"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TEMPLATE_ID = "characterTemplateId"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORIZATION_COUNT = "favorizationCount"; //$NON-NLS-1$

  public ITraitCollectionTemplate getTraitTemplate(String characterTemplateId) {
    int favorizationCount = getFavorizationCount(characterTemplateId);
    return new AttributeTemplate(favorizationCount);
  }

  private int getFavorizationCount(String characterTemplateId) {
    for (IPluginExtension extension : new EclipseExtensionPoint(AttributesPlugin.ID, TEMPLATES_EXTENSION_POINT).getExtensions()) {
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