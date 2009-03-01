package net.sf.anathema.character.spiritualtraits.points.virtues;

import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitGroupTemplate;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;

public class Virtues {

  private final ITraitCollectionModel spiritualTraits;
  private final SpiritualTraitGroupTemplate groupTemplate = new SpiritualTraitGroupTemplate();

  public Virtues(final ITraitCollectionModel spiritualTraits) {
    this.spiritualTraits = spiritualTraits;
  }

  public IBasicTrait[] getTraits() {
    final TraitGroup virtuesGroup = groupTemplate.createVirtuesGroup();
    return spiritualTraits.getTraits(virtuesGroup.getTraitIds());
  }
}