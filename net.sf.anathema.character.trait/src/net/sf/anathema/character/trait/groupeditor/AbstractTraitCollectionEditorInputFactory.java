package net.sf.anathema.character.trait.groupeditor;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.FavorizationInteraction;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.IEditorInput;

public abstract class AbstractTraitCollectionEditorInputFactory implements IEditorInputFactory {

  public AbstractTraitCollectionEditorInputFactory() {
    super();
  }

  @Override
  public final IEditorInput create(
      IFile modelFile,
      ICharacterId characterId,
      URL imageUrl,
      IDisplayNameProvider nameProvider,
      IModelCollection modelProvider) throws PersistenceException, CoreException {
    ICharacterTemplate template = new CharacterTemplateProvider().getTemplate(characterId);
    IEditorInputConfiguration inputConfiguration = createEditorInputConfiguration();
    String modelId = inputConfiguration.getModelId();
    IFavorizationInteraction favorizationHandler = createFavorizationHandler(characterId, template, modelProvider, modelId);
    ITraitCollectionTemplate collectionTemplate = createTemplate(template);
    TraitCollectionContext context = TraitCollectionContext.create(
        characterId,
        modelProvider,
        modelId,
        collectionTemplate.getGroupTemplate());
    return new TraitCollectionEditorInput(
        modelFile,
        imageUrl,
        nameProvider,
        context,
        favorizationHandler,
        inputConfiguration);
  }

  private IFavorizationInteraction createFavorizationHandler(
      ICharacterId characterId,
      ICharacterTemplate template,
      IModelCollection modelProvider,
      String modelId) {
    return new FavorizationInteraction(
        characterId,
        createTemplate(template).getFavorizationTemplate(),
        modelProvider,
        modelId);
  }

  protected abstract ITraitCollectionTemplate createTemplate(ICharacterTemplate template);

  protected abstract IEditorInputConfiguration createEditorInputConfiguration();

  @Override
  public final void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}