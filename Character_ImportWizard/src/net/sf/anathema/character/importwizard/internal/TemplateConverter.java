package net.sf.anathema.character.importwizard.internal;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.runtime.IProvider;

import org.dom4j.Document;
import org.dom4j.Element;

public class TemplateConverter implements IProvider<String> {

  private static final String CONVERTERS_EXTENSION_POINT = "templateconverters"; //$NON-NLS-1$
  private static final String ATTRIB_NEW_TEMPLATE_ID = "newTemplateId"; //$NON-NLS-1$
  private static final String ATTRIB_OLD_TEMPLATE_ID = "oldTemplateId"; //$NON-NLS-1$
  private static final String ATTRIB_OLD_CHARACTER_TYPE = "oldCharacterType"; //$NON-NLS-1$
  private static final String ATTRIB_SUBTYPE = "subtype"; //$NON-NLS-1$
  private static final String TAG_CHARACTER_TYPE = "CharacterType"; //$NON-NLS-1$
  private static final String TAG_STATISTICS = "Statistics"; //$NON-NLS-1$
  private final Document document;

  public TemplateConverter(Document document) {
    this.document = document;
  }

  @Override
  public String get() {
    Element typeElement = document.getRootElement().element(TAG_STATISTICS).element(TAG_CHARACTER_TYPE);
    String characterType = typeElement.getText();
    String template = typeElement.attributeValue(ATTRIB_SUBTYPE);
    return getNewTemplateId(characterType, template);
  }

  private String getNewTemplateId(String characterType, String template) {
    EclipseExtensionPoint point = new EclipseExtensionPoint(
        net.sf.anathema.character.importwizard.plugin.CharacterImportWizardPluginConstants.PLUGIN_ID,
        CONVERTERS_EXTENSION_POINT);
    for (IPluginExtension extension : point.getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        String oldType = element.getAttribute(ATTRIB_OLD_CHARACTER_TYPE);
        String oldTemplate = element.getAttribute(ATTRIB_OLD_TEMPLATE_ID);
        if (oldType.equals(characterType) && oldTemplate.equals(template)) {
          return element.getAttribute(ATTRIB_NEW_TEMPLATE_ID);
        }
      }
    }
    return null;
  }
}