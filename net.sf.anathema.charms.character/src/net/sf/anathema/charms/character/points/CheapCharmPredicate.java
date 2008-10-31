package net.sf.anathema.charms.character.points;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.model.TypeTraitModelIdProvider;

public class CheapCharmPredicate implements IPredicate<String> {

  public static IPredicate<String> create(ICharacterId characterId, IModelCollection modelCollection) {
    ICharacterType characterType = new CharacterTypeFinder().getCharacterType(characterId);
    String traitModelId = new TypeTraitModelIdProvider().getTraitModelId(characterType.getId());
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, traitModelId);
    ITraitCollectionModel model = (ITraitCollectionModel) modelCollection.getModel(modelIdentifier);
    ITraitIdProvider idProvider = new CharmTraitProvider();
    return new CheapCharmPredicate(model, idProvider);
  }

  private final ITraitCollectionModel traits;
  private final ITraitIdProvider idProvider;

  public CheapCharmPredicate(ITraitCollectionModel traits, ITraitIdProvider idProvider) {
    this.traits = traits;
    this.idProvider = idProvider;
  }

  public boolean evaluate(String charmId) {
    String traitId = idProvider.getTraitId(charmId);
    IBasicTrait trait = traits.getTrait(traitId);
    return trait.getStatusManager().getStatus().isCheap();
  }
}