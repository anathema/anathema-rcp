package net.sf.anathema.character.spiritualtraits.points.virtues;

import net.sf.anathema.character.spiritualtraits.model.SpiritualTraitGroupTemplate;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.points.PointWiseBonuspointCalculator;
import net.sf.anathema.character.trait.points.PointwiseCostDto;

public class VirtuesBonuspointCalculatorFactory {

  private final ITraitCollectionModel spiritualTraits;

  public VirtuesBonuspointCalculatorFactory(final ITraitCollectionModel spiritualTraits) {
    this.spiritualTraits = spiritualTraits;
  }

  public PointWiseBonuspointCalculator create() {
    final PointwiseCostDto costDto = new VirtuesCostDtoFactory().create();
    final TraitGroup virtuesGroup = new SpiritualTraitGroupTemplate().createVirtuesGroup();
    final IBasicTrait[] traits = spiritualTraits.getTraits(virtuesGroup.getTraitIds());
    return new PointWiseBonuspointCalculator(costDto, traits);
  }
}