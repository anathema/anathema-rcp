package net.sf.anathema.charms.character.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.resources.TraitMessages;
import net.sf.anathema.charms.extension.CharmProvidingExtensionPoint;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;

public class GenericCharmCollector {

  private final ICharacter character;

  public GenericCharmCollector(ICharacter character) {
    this.character = character;
  }

  public Collection<String> getGenericIdPatterns() {
    String typeId = character.getCharacterType().getId();
    return CharmProvidingExtensionPoint.CreateTreeProvider().getGenericCharms(typeId);
  }

  public List<ICharmId> getLearnedGenerics() {
    List<ICharmId> list = new ArrayList<ICharmId>();
    for (String id : getGenericIdPatterns()) {
      for (String trait : getTraits(id)) {
        list.add(new CharmId(id, trait));
      }
    }
    return list;
  }

  public List<String> getTraits(String genericId) {
    ICharmModel charmModel = (ICharmModel) character.getModel(ICharmModel.MODEL_ID);
    List<String> traits = new ArrayList<String>();
    for (IDisplayTrait trait : new TraitCollector(character).getAllTraits()) {
      String traitId = trait.getTraitType().getId();
      ICharmId charmId = new CharmId(genericId, traitId);
      if (charmModel.isLearned(charmId)) {
        traits.add(new TraitMessages().getNameFor(traitId));
      }
    }
    return traits;
  }
}