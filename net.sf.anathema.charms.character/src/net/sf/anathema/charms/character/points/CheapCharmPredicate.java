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
import net.sf.anathema.charms.character.tree.CharmTraitLookup;
import net.sf.anathema.charms.character.tree.ITraitIdLookup;

public class CheapCharmPredicate implements IPredicate<String> {

  public static IPredicate<String> create(ICharacterId characterId, IModelCollection modelCollection) {
    ICharacterType characterType = new CharacterTypeFinder().getCharacterType(characterId);
    String traitModelId = new TypeTraitModelIdProvider().getTraitModelId(characterType.getId());
    ModelIdentifier modelIdentifier = new ModelIdentifier(characterId, traitModelId);
    ITraitCollectionModel model = (ITraitCollectionModel) modelCollection.getModel(modelIdentifier);
    ITraitIdLookup idProvider = new CharmTraitLookup();
    return new CheapCharmPredicate(model, idProvider);
  }

  private final ITraitCollectionModel traits;
  private final ITraitIdLookup idLookup;

  public CheapCharmPredicate(ITraitCollectionModel traits, ITraitIdLookup idLookup) {
    this.traits = traits;
    this.idLookup = idLookup;
  }

  public boolean evaluate(String charmId) {
    String traitId = idLookup.getTraitId(charmId);
    IBasicTrait trait = traits.getTrait(traitId);
    return trait.getStatusManager().getStatus().isCheap();
  }
}