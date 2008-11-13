package net.sf.anathema.character.abilities.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public class TraitListFactory {

  public List<IBasicTrait> createFrom(ITraitCollectionModel model) {
    ArrayList<IBasicTrait> list = new ArrayList<IBasicTrait>();
    for (IBasicTrait trait : model.getTraits()) {
      if (model.getSubTraits(trait.getTraitType().getId()).isEmpty()) {
        list.add(trait);
      }
      else {
        list.addAll(model.getSubTraits(trait.getTraitType().getId()));
      }
    }
    return list;
  }
}