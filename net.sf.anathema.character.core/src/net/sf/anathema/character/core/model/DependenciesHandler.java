package net.sf.anathema.character.core.model;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

import org.eclipse.core.runtime.IStatus;

public class DependenciesHandler {

  private static final String EXTENSION_ID = "modeldependencies"; //$NON-NLS-1$
  private static final String ATTRIB_CLASS = "class"; //$NON-NLS-1$
  private final ICharacterTemplateProvider templateProvider;

  public DependenciesHandler(ICharacterTemplateProvider templateProvider) {
    this.templateProvider = templateProvider;
  }

  public Iterable<String> getNeededIds(IModelIdentifier identifier) {
    ICharacterTemplate characterTemplate = templateProvider.getTemplate(identifier.getCharacterId());
    String characterTypeId = characterTemplate.getCharacterTypeId();
    List<String> neededIds = new ArrayList<String>();
    for (IPluginExtension extension : new EclipseExtensionPoint(CharacterCorePlugin.ID, EXTENSION_ID).getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        try {
          IDependencyProvider provider = element.getAttributeAsObject(ATTRIB_CLASS, IDependencyProvider.class);
          String modelId = identifier.getModelId();
          neededIds.addAll(provider.getNeededIds(characterTypeId, modelId));
        }
        catch (ExtensionException e) {
          CharacterCorePlugin.getDefaultInstance().log(
              IStatus.ERROR,
              MessageFormat.format(Messages.DependenciesHandler_Error, identifier),
              e);
        }
      }
    }
    return neededIds;
  }
}