package net.sf.anathema.character.freebies.attributes.coverage;

import net.sf.anathema.character.attributes.points.DummyTraitCollection;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.lib.util.IIdentificate;

public class TraitCollectionObjectMother {
  public static DummyTraitCollection createTraitCollection(IIdentificate... traitIds) {
    DummyTraitCollection collection = new DummyTraitCollection();
    for (IIdentificate id : traitIds) {
      BasicTrait basicTrait = new BasicTrait(id);
      collection.addTrait(basicTrait);
    }
    return collection;
  }
}
