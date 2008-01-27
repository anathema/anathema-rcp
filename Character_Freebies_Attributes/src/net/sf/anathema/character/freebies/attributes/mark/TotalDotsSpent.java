package net.sf.anathema.character.freebies.attributes.mark;

import java.util.Map;

import net.sf.anathema.character.freebies.attributes.AttributePriorityFreebies;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;

public class TotalDotsSpent implements ITotalDotsSpent {

  private final ITraitCollectionContext context;

  public TotalDotsSpent(ITraitCollectionContext context) {
    this.context = context;
  }
  
  @Override
  public int get(PriorityGroup priority) {
    Map<PriorityGroup, Integer> hashMap = new AttributePriorityFreebies().getEmpty();
    Dots dots = new AttributePointCalculator(hashMap, context.getCollection(), context.getTraitGroups()).dotsFor(priority);
    return dots.spentTotally();
  }
}