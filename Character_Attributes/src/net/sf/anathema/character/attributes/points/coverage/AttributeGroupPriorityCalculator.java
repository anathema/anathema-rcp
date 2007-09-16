package net.sf.anathema.character.attributes.points.coverage;

import java.util.Arrays;
import java.util.Comparator;

import net.sf.anathema.character.attributes.points.AttributePointCalculator;
import net.sf.anathema.character.attributes.points.Dots;
import net.sf.anathema.character.attributes.points.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.lib.collection.ArrayUtilities;

public class AttributeGroupPriorityCalculator {

  private final ITraitCollectionContext context;

  public AttributeGroupPriorityCalculator(ITraitCollectionContext context) {
    this.context = context;
  }

  public PriorityGroup getPriority(ITraitGroup traitGroup) {
    final ITraitGroup[] groups = context.getTraitGroups();
    final Dots[] dots = new AttributePointCalculator(context.getCollection(), groups).getDotsForGroups();
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
      return AttributePointCalculator.PRIMARY;
    }
    else if (index == 1) {
      return AttributePointCalculator.SECONDARY;
    }
    return AttributePointCalculator.TERTIARY;
  }
}