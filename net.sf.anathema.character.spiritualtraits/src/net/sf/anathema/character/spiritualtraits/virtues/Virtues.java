package net.sf.anathema.character.spiritualtraits.virtues;

import java.util.Arrays;
import java.util.Iterator;

import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitGroupTemplate;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;

public class Virtues implements Iterable<IBasicTrait> {

  private final ITraitCollectionModel spiritualTraits;
  private final SpiritualTraitGroupTemplate groupTemplate = new SpiritualTraitGroupTemplate();

  public Virtues(final ITraitCollectionModel spiritualTraits) {
    this.spiritualTraits = spiritualTraits;
  }

  public IBasicTrait[] getTraits() {
    final TraitGroup virtuesGroup = groupTemplate.createVirtuesGroup();
    return spiritualTraits.getTraits(virtuesGroup.getTraitIds());
  }

  @Override
  public Iterator<IBasicTrait> iterator() {
    return Arrays.asList(getTraits()).iterator();
  }
}