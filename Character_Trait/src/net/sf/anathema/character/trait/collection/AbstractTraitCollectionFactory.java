package net.sf.anathema.character.trait.collection;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.AbstractModelFactory;
import net.sf.anathema.character.core.model.IModelInitializer;
import net.sf.anathema.character.core.model.ModelInitializer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.ui.IUpdatable;

import org.eclipse.core.runtime.CoreException;

public abstract class AbstractTraitCollectionFactory extends
    AbstractModelFactory<ITraitCollectionTemplate, ITraitCollectionModel> {

  @Override
  public final IModelInitializer createInitializer(
      IContentHandle contentHandler,
      final ICharacterTemplate template,
      final IModelIdentifier identifier) throws PersistenceException, CoreException {
    final ITraitCollectionModel model = create(contentHandler, template);
    final IUpdatable updatable = new TraitCollectionUpdatable(template, identifier, model);
    model.setDependencyUpdatable(updatable);
    return new ModelInitializer(model, contentHandler, identifier) {
      @Override
      public void initialize() {
        final IFavorizationTemplate favorizationTemplate = createModelTemplate(template).getFavorizationTemplate();
        for (IBasicTrait trait : model.getTraits()) {
          if (trait.getStatusManager().getStatus() instanceof DefaultStatus
              && favorizationTemplate.isRequiredFavored(trait.getTraitType())) {
            trait.getStatusManager().setStatus(new FavoredStatus());
          }
        }
        updatable.update();
        super.initialize();
      }
    };
  }
}
