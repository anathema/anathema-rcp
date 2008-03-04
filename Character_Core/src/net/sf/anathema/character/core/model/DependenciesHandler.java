package net.sf.anathema.character.core.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.ICharacterTemplateProvider;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.plugin.internal.CharacterCorePlugin;

public class DependenciesHandler {

  private static final String ATTRIB_NEEDED_MODEL_ID = "neededModelId"; //$NON-NLS-1$
  private static final String ATTRIB_DEPENDENT_MODEL_ID = "dependentModelId"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TYPE = "characterType"; //$NON-NLS-1$
  private static final String EXTENSION_ID = "modeldependencies"; //$NON-NLS-1$
  private final ICharacterTemplateProvider templateProvider;

  public DependenciesHandler(ICharacterTemplateProvider templateProvider) {
    this.templateProvider = templateProvider;
  }

  public Iterable<String> getNeededIds(IModelIdentifier identifier) {
    List<String> neededIds = new ArrayList<String>();
    ICharacterTemplate characterTemplate = templateProvider.getTemplate(identifier.getCharacterId());
    String characterTypeId = characterTemplate.getCharacterTypeId();
    for (IPluginExtension extension : new EclipseExtensionPoint(CharacterCorePlugin.ID, EXTENSION_ID).getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        if (element.getAttribute(ATTRIB_CHARACTER_TYPE).equals(characterTypeId)
            && element.getAttribute(ATTRIB_DEPENDENT_MODEL_ID).equals(identifier.getModelId())) {
          neededIds.add(element.getAttribute(ATTRIB_NEEDED_MODEL_ID));
        }
      }
    }
    return neededIds;
  }
}