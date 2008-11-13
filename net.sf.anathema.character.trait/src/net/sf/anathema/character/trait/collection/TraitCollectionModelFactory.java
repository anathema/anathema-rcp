package net.sf.anathema.character.trait.collection;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.lib.util.Identificate;

public class TraitCollectionModelFactory {


  public static ITraitCollectionModel create(ITraitGroup[] groups) {
    List<IBasicTrait> basicTraits = new ArrayList<IBasicTrait>();
    for (ITraitGroup group : groups) {
      for (String traitId : group.getTraitIds()) {
        basicTraits.add(new BasicTrait(new Identificate(traitId)));
      }
    }
    return new TraitCollection(basicTraits.toArray(new BasicTrait[basicTraits.size()]));
  }
}