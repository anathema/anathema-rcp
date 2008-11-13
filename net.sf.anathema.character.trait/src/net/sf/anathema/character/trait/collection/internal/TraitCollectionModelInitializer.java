package net.sf.anathema.character.trait.collection.internal;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelInitializer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollectionUpdatable;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.character.trait.status.ITraitStatus;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.lib.util.IIdentificate;

public class TraitCollectionModelInitializer extends ModelInitializer {

  private final ITraitCollectionModel traitCollection;
  private final IUpdatable updatable;
  private final ITraitCollectionTemplate modelTemplate;

  public TraitCollectionModelInitializer(
      ITraitCollectionModel traitCollection,
      IContentHandle contentHandler,
      IModelIdentifier identifier,
      ICharacterTemplate template,
      ITraitCollectionTemplate modelTemplate) {
    super(traitCollection, contentHandler, identifier);
    this.traitCollection = traitCollection;
    this.modelTemplate = modelTemplate;
    updatable = new TraitCollectionUpdatable(template, identifier, traitCollection);
    traitCollection.setDependencyUpdatable(updatable);
  }

  @Override
  public void initialize() {
    super.initialize();
    initializeRequiredFavored(modelTemplate.getFavorizationTemplate());
    updatable.update();
  }

  private void initializeRequiredFavored(final IFavorizationTemplate template) {
    for (IBasicTrait trait : traitCollection.getTraits()) {
      ITraitStatus status = trait.getStatusManager().getStatus();
      IIdentificate traitType = trait.getTraitType();
      if (status instanceof DefaultStatus && template.isRequiredFavored(traitType)) {
        trait.getStatusManager().setStatus(new FavoredStatus());
      }
    }
  }
}