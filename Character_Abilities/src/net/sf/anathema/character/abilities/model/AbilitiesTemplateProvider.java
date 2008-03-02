package net.sf.anathema.character.abilities.model;

import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;


public class AbilitiesTemplateProvider {

  private static final String TEMPLATES_EXTENSION_POINT = "templates"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TEMPLATE_ID = "characterTemplateId"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORIZATION_COUNT = "favorizationCount"; //$NON-NLS-1$

  public ITraitCollectionTemplate getAttributeTemplate(String characterTemplateId) {
    int favorizationCount = getFavorizationCount(characterTemplateId);
    return new AbilitiesTemplate(favorizationCount);
  }

  private int getFavorizationCount(String characterTemplateId) {
    return 0;
//    for (IPluginExtension extension : new EclipseExtensionPoint(AttributesPlugin.ID, TEMPLATES_EXTENSION_POINT).getExtensions()) {
//      for (IExtensionElement element : extension.getElements()) {
//        String templateId = element.getAttribute(ATTRIB_CHARACTER_TEMPLATE_ID);
//        if (templateId.equals(characterTemplateId)) {
//          if (element.hasAttribute(ATTRIB_FAVORIZATION_COUNT)) {
//            return element.getIntegerAttribute(ATTRIB_FAVORIZATION_COUNT);
//          }
//          return 0;
//        }
//      }
//    }
//    return 0;
  }
}