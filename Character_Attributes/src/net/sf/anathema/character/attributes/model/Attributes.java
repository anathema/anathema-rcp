package net.sf.anathema.character.attributes.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollection;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.rules.ITraitTemplate;
import net.sf.anathema.lib.util.Identificate;

public class Attributes {

  public static final String MODEL_ID = "net.sf.anathema.character.attributes.model"; //$NON-NLS-1$
  
  public static ITraitCollectionModel create(ITraitGroup[] groups, ITraitTemplate template) {
    List<IBasicTrait> basicTraits = new ArrayList<IBasicTrait>();
    for (ITraitGroup group : groups) {
      for (String traitId : group.getTraitIds()) {
        BasicTrait basicTrait = new BasicTrait(new Identificate(traitId));
        basicTraits.add(basicTrait);
        basicTrait.getCreationModel().setValue(template.getMinimalValue());
      }
    }
    return new TraitCollection(basicTraits.toArray(new BasicTrait[basicTraits.size()]));
  }
}