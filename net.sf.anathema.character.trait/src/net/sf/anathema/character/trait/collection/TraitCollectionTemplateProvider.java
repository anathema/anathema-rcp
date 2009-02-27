package net.sf.anathema.character.trait.collection;

import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.plugin.CharacterTraitPlugin;
import net.sf.anathema.character.trait.template.FavorizationTemplateExtensionPoint;
import net.sf.anathema.character.trait.template.IFavorizationTemplateMap;

public abstract class TraitCollectionTemplateProvider implements ITraitCollectionTemplateProvider {

  private static final String FAVORIZATION_EXTENSION_POINT = "favorization"; //$NON-NLS-1$
  private final IFavorizationTemplateMap templateProvider;

  private static IFavorizationTemplateMap createMapFromPlatform(final String modelId) {
    final EclipseExtensionPoint extensionPoint = new EclipseExtensionPoint(
        CharacterTraitPlugin.PLUGIN_ID,
        FAVORIZATION_EXTENSION_POINT);
    return new FavorizationTemplateExtensionPoint(modelId, extensionPoint);
  }

  public TraitCollectionTemplateProvider(final String modelId) {
    this(createMapFromPlatform(modelId));
  }

  public TraitCollectionTemplateProvider(final IFavorizationTemplateMap templateProvider) {
    this.templateProvider = templateProvider;
  }

  public ITraitCollectionTemplate getTraitTemplate(final String characterTemplateId) {
    final IFavorizationTemplate favoredTemplate = getFavorizationTemplate(characterTemplateId);
    final ITraitGroupTemplate groupTemplate = createGroupTemplate(characterTemplateId);
    return new TraitCollectionTemplate(groupTemplate, favoredTemplate);
  }

  protected abstract ITraitGroupTemplate createGroupTemplate(String characterTemplateId);

  private IFavorizationTemplate getFavorizationTemplate(final String characterTemplateId) {
    return templateProvider.getTemplate(characterTemplateId);
  }
}