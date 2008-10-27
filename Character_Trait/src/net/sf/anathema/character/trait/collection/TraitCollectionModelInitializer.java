package net.sf.anathema.character.trait.collection;

import net.sf.anathema.basics.eclipse.resource.IContentHandle;
import net.sf.anathema.character.core.character.ICharacterTemplate;
import net.sf.anathema.character.core.character.IModelIdentifier;
import net.sf.anathema.character.core.model.ModelInitializer;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;
import net.sf.anathema.character.trait.model.ITraitCollectionTemplate;
import net.sf.anathema.character.trait.status.DefaultStatus;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.ui.IUpdatable;

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
    final IFavorizationTemplate favorizationTemplate = modelTemplate.getFavorizationTemplate();
    for (IBasicTrait trait : traitCollection.getTraits()) {
      if (trait.getStatusManager().getStatus() instanceof DefaultStatus
          && favorizationTemplate.isRequiredFavored(trait.getTraitType())) {
        trait.getStatusManager().setStatus(new FavoredStatus());
      }
    }
    updatable.update();
  }
}