package net.sf.anathema.character.trait.groupeditor;

import java.net.URL;

import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.trait.IFavorizationHandler;
import net.sf.anathema.character.trait.model.ITraitGroupTemplate;
import net.sf.anathema.character.trait.model.ITraitTemplateFactory;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.character.trait.persistence.ITraitCollectionTemplate;
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
    IFavorizationHandler favorizationHandler = createFavorizationHandler(characterId, template, modelProvider, modelId);
    ITraitGroupTemplate groupTemplate = createGroupTemplate(template);
    ITraitTemplateFactory templateFactory = createTemplateFactory(template);
    TraitCollectionContext context = TraitCollectionContext.create(
        characterId,
        modelProvider,
        modelId,
        groupTemplate,
        templateFactory);
    return new TraitCollectionEditorInput(
        modelFile,
        imageUrl,
        nameProvider,
        context,
        favorizationHandler,
        inputConfiguration);
  }

  private IFavorizationHandler createFavorizationHandler(
      ICharacterId characterId,
      ICharacterTemplate template,
      IModelCollection modelProvider,
      String modelId) {
    return new FavorizationHandler(characterId, createTemplate(template), modelProvider, modelId);
  }

  protected abstract ITraitCollectionTemplate createTemplate(ICharacterTemplate template);

  protected abstract IEditorInputConfiguration createEditorInputConfiguration();

  protected abstract ITraitGroupTemplate createGroupTemplate(ICharacterTemplate template);

  protected abstract ITraitTemplateFactory createTemplateFactory(ICharacterTemplate template);

  @Override
  public final void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}