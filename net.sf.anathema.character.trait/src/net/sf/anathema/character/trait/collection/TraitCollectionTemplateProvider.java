package net.sf.anathema.character.trait.collection;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;

public abstract class TraitCollectionTemplateProvider implements ITraitCollectionTemplateProvider {

  private static final String FAVORIZATION_EXTENSION_POINT = "favorization"; //$NON-NLS-1$
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
    IPluginExtension[] extensions = new EclipseExtensionPoint(
        CharacterTraitPlugin.PLUGIN_ID,
        FAVORIZATION_EXTENSION_POINT).getExtensions();
    FavorizationTemplateProvider templateProvider = new FavorizationTemplateProvider(modelId, extensions);
    return templateProvider.getFavorizationTemplate(characterTemplateId);
  }
}