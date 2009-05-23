package net.sf.anathema.charms.character.evaluation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.model.IMainTraitModelProvider;
import net.sf.anathema.character.trait.model.MainTraitModelProvider;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

public class GenericCharmCharacter implements IGenericCharmCharacter {

  private final ICharacter character;
  private final IMainTraitModelProvider traitModelProvider;

  public GenericCharmCharacter(ICharacter character) {
    this(character, new MainTraitModelProvider());
  }

  public GenericCharmCharacter(ICharacter character, IMainTraitModelProvider traitModelProvider) {
    this.character = character;
    this.traitModelProvider = traitModelProvider;
  }

  public List<ICharmId> getLearnedCharmIds(String genericId) {
    List<ICharmId> learnedCharmIds = new ArrayList<ICharmId>();
    for (ICharmId charmId : getAllCharmIds(genericId)) {
      if (new HasLearnedCharm().has(character, charmId.getId())) {
        learnedCharmIds.add(charmId);
      }
    }
    return learnedCharmIds;
  }

  public List<ICharmId> getAllCharmIds(String genericId) {
    String mainModel = traitModelProvider.getFor(character.getCharacterType().getId());
    ITraitCollectionModel traits = (ITraitCollectionModel) character.getModel(mainModel);
    List<ICharmId> charmIds = new ArrayList<ICharmId>();
    for (IBasicTrait trait : traits) {
      String traitId = trait.getTraitType().getId();
      charmIds.add(new CharmId(genericId, traitId));
    }
    return charmIds;
  }

  @Override
  public Collection<String> getGenericIdPatterns(IGenericCharmTree charmTree) {
    String characterType = character.getCharacterType().getId();
    return charmTree.getGenericIdPattersFor(characterType);
  }
}