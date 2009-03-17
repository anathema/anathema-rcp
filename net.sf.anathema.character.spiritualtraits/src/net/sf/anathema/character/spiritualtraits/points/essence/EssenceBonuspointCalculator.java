package net.sf.anathema.character.spiritualtraits.points.essence;

import static net.sf.anathema.character.spiritualtraits.plugin.IPluginConstants.*;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.points.AbstractPointWiseBonuspointCalculator;
import net.sf.anathema.character.trait.points.PointwiseCostDto;

public class EssenceBonuspointCalculator extends AbstractPointWiseBonuspointCalculator {

  private final ITraitCollectionModel spiritualTraits;

  public EssenceBonuspointCalculator(ITraitCollectionModel spiritualTraits, PointwiseCostDto pointDto) {
    super(pointDto);
    this.spiritualTraits = spiritualTraits;
  }

  @Override
  protected IBasicTrait[] getCalculationTraits() {
    IBasicTrait essence = spiritualTraits.getTrait(ESSENCE_ID);
    return new IBasicTrait[] { essence };
  }
}