package net.sf.anathema.character.freebies.configuration;

import org.eclipse.osgi.util.NLS;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.basics.eclipse.logging.Logger;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.freebies.plugin.ICharacterFreebiesPluginConstants;

public final class CreditManager implements ICreditManager {

  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private static final String TAG_CREDIT_PROVIDER = "creditProvider"; //$NON-NLS-1$
  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_TEMPLATE_ID = "templateId"; //$NON-NLS-1$
  private static final String EXTENSION_ID = "credits"; //$NON-NLS-1$
  private final ICharacterTemplateProvider templateProvider;
  private final IPluginExtension[] extensions;

  public CreditManager() {
    this(
        new EclipseExtensionPoint(ICharacterFreebiesPluginConstants.PLUGIN_ID, EXTENSION_ID),
        new CharacterTemplateProvider());
  }

  public CreditManager(IExtensionProvider extensionProvider, ICharacterTemplateProvider templateProvider) {
    this(templateProvider, extensionProvider.getExtensions());
  }

  public CreditManager(ICharacterTemplateProvider templateProvider, IPluginExtension... extensions) {
    this.extensions = extensions;
    this.templateProvider = templateProvider;
  }

  private Integer getCredit(String templateId, String creditId) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        if (element.getName().equals(TAG_CREDIT_PROVIDER)) {
          if (creditId.equals(element.getAttribute(ATTRIB_ID))) {
            try {
              ICreditProvider provider = element.getAttributeAsObject(ATTRIB_CLASS, ICreditProvider.class);
              return provider.getCredit(templateId);
            }
            catch (ExtensionException e) {
              new Logger(ICharacterFreebiesPluginConstants.PLUGIN_ID).error(NLS.bind(
                  Messages.CreditManager_ErrorInstantiatingHandler,
                  creditId), e);
              return null;
            }
          }
          continue;
        }
        if (!element.getAttribute(TAG_TEMPLATE_ID).equals(templateId)) {
          continue;
        }
        for (IExtensionElement child : element.getElements()) {
          if (child.getAttribute(ATTRIB_ID).equals(creditId)) {
            return child.getIntegerAttribute(ATTRIB_VALUE);
          }
        }
      }
    }
    return null;
  }

  @Override
  public int getCredit(ICharacterId characterId, String creditId) {
    ICharacterTemplate template = templateProvider.getTemplate(characterId);
    Integer credit = getCredit(template.getId(), creditId);
    if (credit == null) {
      return 0;
    }
    return credit;
  }

  @Override
  public boolean hasCredit(String templateId, String creditId) {
    return getCredit(templateId, creditId) != null;
  }
}