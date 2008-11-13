package net.sf.anathema.character.trait.collection;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelInitializer;
import net.sf.anathema.character.trait.collection.internal.TraitCollectionModelInitializer;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.lib.exception.PersistenceException;

import org.eclipse.core.runtime.CoreException;

public abstract class AbstractTraitCollectionFactory extends
    AbstractModelFactory<ITraitCollectionTemplate, ITraitCollectionModel> {

  @Override
  public final IModelInitializer createInitializer(
      IContentHandle contentHandler,
      final ICharacterTemplate template,
      final IModelIdentifier identifier) throws PersistenceException, CoreException {
    final ITraitCollectionModel model = create(contentHandler, template);
    final ITraitCollectionTemplate modelTemplate = createModelTemplate(template);
    return new TraitCollectionModelInitializer(model, contentHandler, identifier, template, modelTemplate);
  }
}
