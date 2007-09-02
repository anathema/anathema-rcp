package net.sf.anathema.character.freebies.configuration;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.core.template.ICharacterTemplate;
import net.sf.anathema.character.core.template.ICharacterTemplateProvider;

public final class CreditManager implements ICreditManager {

  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String ATTRIB_ID = "id"; //$NON-NLS-1$
  private static final String TAG_TEMPLATE_ID = "templateId"; //$NON-NLS-1$
  private static final String EXTENSION_ID = "net.sf.anathema.character.freebies.credits"; //$NON-NLS-1$
  private final IExtensionProvider extensionProvider;
  private final ICharacterTemplateProvider templateProvider;

  public CreditManager() {
    this(new EclipseExtensionProvider(), new CharacterTemplateProvider());
  }

  public CreditManager(IExtensionProvider extensionProvider, ICharacterTemplateProvider templateProvider) {
    this.extensionProvider = extensionProvider;
    this.templateProvider = templateProvider;
  }

  private Integer getCredit(String templateId, String creditId) {
    for (IPluginExtension extension : extensionProvider.getExtensions(EXTENSION_ID)) {
      for (IExtensionElement element : extension.getElements()) {
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