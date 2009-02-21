package net.sf.anathema.character.trait.groupeditor;

import java.net.URL;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.repository.treecontent.itemtype.IDisplayNameProvider;
import net.sf.anathema.character.core.character.Character;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.core.repository.IEditorInputFactory;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.trait.IFavorizationInteraction;
import net.sf.anathema.character.trait.collection.FavorizationInteraction;
import net.sf.anathema.character.trait.collection.ITraitCollectionTemplateProvider;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.model.TraitCollectionContext;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;

public abstract class AbstractTraitCollectionEditorInputFactory extends AbstractExecutableExtension implements
    IEditorInputFactory {

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
    ICharacter character = Character.From(characterId, modelProvider);
    IFavorizationInteraction favorizationHandler = createFavorizationInteraction(character, template, modelId);
    ITraitCollectionTemplate collectionTemplate = createTraitCollectionTemplate(template);
    TraitCollectionContext context = TraitCollectionContext.create(
        character,
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

  private IFavorizationInteraction createFavorizationInteraction(
      IModelContainer modelContainer,
      ICharacterTemplate template,
      String modelId) {
    IFavorizationTemplate favorizationTemplate = createTraitCollectionTemplate(template).getFavorizationTemplate();
    return new FavorizationInteraction(modelContainer, favorizationTemplate, modelId);
  }

  protected abstract IEditorInputConfiguration createEditorInputConfiguration();

  protected abstract ITraitCollectionTemplateProvider getTraitTemplateProvider();

  private final ITraitCollectionTemplate createTraitCollectionTemplate(ICharacterTemplate characterTemplate) {
    return getTraitTemplateProvider().getTraitTemplate(characterTemplate.getId());
  }
}