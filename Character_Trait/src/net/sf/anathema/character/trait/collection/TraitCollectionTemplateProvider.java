package net.sf.anathema.character.trait.collection;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

public abstract class TraitCollectionTemplateProvider implements ITraitCollectionTemplateProvider {

  private static final String FAVORIZATION_EXTENSION_POINT = "favorization"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TEMPLATE_ID = "characterTemplateId"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORED_COUNT = "favoredCount"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_ID = "modelId"; //$NON-NLS-1$
  private static final String ATTRIB_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private final String modelId;

  public TraitCollectionTemplateProvider(String modelId) {
    this.modelId = modelId;
  }

  public ITraitCollectionTemplate getTraitTemplate(String characterTemplateId) {
    IFavorizationTemplate favoredTemplate = getFavorizationTemplate(characterTemplateId);
    ITraitGroupTemplate groupTemplate = createGroupTemplate(characterTemplateId);
    return new TraitCollectionTemplate(groupTemplate, favoredTemplate);
  }

  protected abstract ITraitGroupTemplate createGroupTemplate(String characterTemplateId);

  private IFavorizationTemplate getFavorizationTemplate(String characterTemplateId) {
    for (IPluginExtension extension : new EclipseExtensionPoint(
        CharacterTraitPlugin.PLUGIN_ID,
        FAVORIZATION_EXTENSION_POINT).getExtensions()) {
      for (IExtensionElement element : extension.getElements()) {
        String templateId = element.getAttribute(ATTRIB_CHARACTER_TEMPLATE_ID);
        String currentModelId = element.getAttribute(ATTRIB_MODEL_ID);
        if (templateId.equals(characterTemplateId) && currentModelId.equals(modelId)) {
          return createTemplate(element);
        }
      }
    }
    return new FavorizationTemplate(0);
  }

  private IFavorizationTemplate createTemplate(IExtensionElement element) {
    List<String> requiredFavored = new ArrayList<String>();
    int favoredCount = element.hasAttribute(ATTRIB_FAVORED_COUNT)
        ? element.getIntegerAttribute(ATTRIB_FAVORED_COUNT)
        : 0;
    for (IExtensionElement traitReference : element.getElements() ) {
      requiredFavored.add(traitReference.getAttribute(ATTRIB_TRAIT_ID));
    }
    return new FavorizationTemplate(favoredCount, requiredFavored);
  }
}