package net.sf.anathema.character.freebies.configuration.internal;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionProvider;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.freebies.configuration.ICreditManager;
import net.sf.anathema.character.freebies.configuration.IFreebiesHandler;
import net.sf.anathema.character.freebies.view.IFreebiesConfigurationsProvider;

public class FreebiesConfigurationExtensionPoint implements IFreebiesConfigurationsProvider {

  private static final String FREEBIES_CONFIGURATION_ID = "net.sf.anathema.character.freebies.configurations"; //$NON-NLS-1$
  private final ICreditManager creditManager;
  private EclipseExtensionProvider extensionProvider = new EclipseExtensionProvider();

  public FreebiesConfigurationExtensionPoint(ICreditManager creditManager) {
    this.creditManager = creditManager;
  }

  public IFreebiesConfiguration[] get(ICharacterId characterId) {
    String templateId = new CharacterTemplateProvider().getTemplate(characterId).getId();
    List<IFreebiesConfiguration> configurations = new ArrayList<IFreebiesConfiguration>();
    for (IPluginExtension extension : extensionProvider.getExtensions(FREEBIES_CONFIGURATION_ID)) {
      for (IExtensionElement element : extension.getElements()) {
        String entryName = element.getAttribute("name"); //$NON-NLS-1$
        try {
          IFreebiesHandler handler = element.getAttributeAsObject("handlerClass", IFreebiesHandler.class); //$NON-NLS-1$
          if (creditManager.hasCredit(templateId, handler.getCreditId())) {
            configurations.add(new FreebiesConfiguration(entryName, handler, creditManager));
          }
        }
        catch (ExtensionException e) {
          configurations.add(new MissingFreebiesConfiguration(entryName));
        }
      }
    }
    return configurations.toArray(new IFreebiesConfiguration[configurations.size()]);
  }
}