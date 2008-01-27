package net.sf.anathema.character.freebies.attributes.coverage;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator;
import net.sf.anathema.character.freebies.attributes.calculation.Dots;
import net.sf.anathema.character.freebies.attributes.calculation.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.lib.collection.ArrayUtilities;

public class AttributeGroupPriorityCalculator {

  private final ITraitCollectionModel model;
  private final ITraitGroup[] groups;
  private final Map<PriorityGroup, Integer> creditByPriority;

  public AttributeGroupPriorityCalculator(ITraitCollectionContext context, Map<PriorityGroup, Integer> creditByPriority) {
    this(context.getTraitGroups(), context.getCollection(), creditByPriority);
  }

  public AttributeGroupPriorityCalculator(
      final ITraitGroup[] groups,
      final ITraitCollectionModel model,
      Map<PriorityGroup, Integer> creditByPriority) {
    this.groups = groups;
    this.model = model;
    this.creditByPriority = creditByPriority;
  }

  public PriorityGroup getPriority(ITraitGroup traitGroup) {
    if (groups.length == 1) {
      return AttributePointCalculator.PriorityGroup.Primary;
    }
    final Dots[] dots = new AttributePointCalculator(creditByPriority, model, groups).getDotsForGroups();
    Arrays.sort(groups, new Comparator<ITraitGroup>() {
      @Override
      public int compare(ITraitGroup firstGroup, ITraitGroup secondGroup) {
        int firstIndex = ArrayUtilities.indexOf(groups, firstGroup);
        int secondIndex = ArrayUtilities.indexOf(groups, secondGroup);
        return dots[secondIndex].spentTotally() - dots[firstIndex].spentTotally();
      }
    });
    int index = ArrayUtilities.indexOf(groups, traitGroup);
    if (index == 0) {
      return AttributePointCalculator.PriorityGroup.Primary;
    }
    else if (index == 1) {
      return AttributePointCalculator.PriorityGroup.Secondary;
    }
    return AttributePointCalculator.PriorityGroup.Tertiary;
  }
}