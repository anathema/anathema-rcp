package net.sf.anathema.character.attributes.points.coverage;

import java.util.Arrays;
import java.util.Comparator;

import net.sf.anathema.character.attributes.points.AttributePointCalculator;
import net.sf.anathema.character.attributes.points.Dots;
import net.sf.anathema.character.attributes.points.AttributePointCalculator.PriorityGroup;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.lib.collection.ArrayUtilities;

public class AttributeGroupPriorityCalculator {

  private final ITraitCollectionModel model;
  private final ITraitGroup[] groups;

  public AttributeGroupPriorityCalculator(ITraitCollectionContext context) {
    this(context.getTraitGroups(), context.getCollection());
  }
  
  public AttributeGroupPriorityCalculator(final ITraitGroup[] groups, final ITraitCollectionModel model) {
    this.groups = groups;
    this.model = model;
  }

  public PriorityGroup getPriority(ITraitGroup traitGroup) {
    final Dots[] dots = new AttributePointCalculator(model, groups).getDotsForGroups();
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