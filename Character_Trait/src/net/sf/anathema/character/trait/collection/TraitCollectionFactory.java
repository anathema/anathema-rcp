package net.sf.anathema.character.trait.collection;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.lib.util.Identificate;

public class TraitCollectionFactory {


  public static ITraitCollectionModel create(ITraitGroup[] groups) {
    List<IBasicTrait> basicTraits = new ArrayList<IBasicTrait>();
    for (ITraitGroup group : groups) {
      for (String traitId : group.getTraitIds()) {
        BasicTrait basicTrait = new BasicTrait(new Identificate(traitId));
        basicTraits.add(basicTrait);
        // TODO: Case 183: Reaktiviere das Initialisieren des Models
        //basicTrait.getCreationModel().setValue(factory.getMinimalValue(traitId));
      }
    }
    return new TraitCollection(basicTraits.toArray(new BasicTrait[basicTraits.size()]));
  }
}