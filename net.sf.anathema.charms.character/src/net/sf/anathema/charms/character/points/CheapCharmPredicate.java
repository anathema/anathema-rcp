package net.sf.anathema.charms.character.points;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.character.core.character.ICharacterType;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.character.ModelIdentifier;
import net.sf.anathema.character.core.type.CharacterTypeFinder;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.model.MainTraitModelProvider;
import net.sf.anathema.charms.tree.ICharmId;

public class CheapCharmPredicate implements IPredicate<ICharmId> {

  public static IPredicate<ICharmId> From(IModelCollection modelCollection, ICharacterId characterId) {
    ICharacterType characterType = new CharacterTypeFinder().getCharacterType(characterId);
    String traitModelId = new MainTraitModelProvider().getFor(characterType.getId());
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, traitModelId);
    ITraitCollectionModel model = (ITraitCollectionModel) modelCollection.getModel(modelIdentifier);
    return new CheapCharmPredicate(model);
  }

  private final ITraitCollectionModel traits;

  public CheapCharmPredicate(ITraitCollectionModel traits) {
    this.traits = traits;
  }

  public boolean evaluate(ICharmId charmId) {
    String traitId = charmId.getPrimaryTrait();
    IBasicTrait trait = traits.getTrait(traitId);
    return trait.getStatusManager().getStatus().isCheap();
  }
}