package net.sf.anathema.character.freebies.attributes.calculation;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.attributes.points.IAttributeConstants;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.ITraitGroup;

public class Dots {

  private final IBasicTrait[] traits;
  private int calculationBase = IAttributeConstants.ATTRIBUTE_CALCULATION_BASE;

  private static IBasicTrait[] findTraits(final ITraitCollectionModel collection, ITraitGroup group) {
    return ArrayUtilities.transform(group.getTraitIds(), IBasicTrait.class, new ITransformer<String, IBasicTrait>() {
      @Override
      public IBasicTrait transform(String arg0) {
        return collection.getTrait(arg0);
      }
    });
  }

  public Dots(ITraitCollectionModel collection, ITraitGroup group) {
    this(findTraits(collection, group));
  }

  public Dots(IBasicTrait... traits) {
    this.traits = traits;
  }

  public int spentTotally() {
    int pointsSpent = 0;
    for (IBasicTrait trait : traits) {
      int creationValue = trait.getCreationModel().getValue();
      pointsSpent += Math.max(0, creationValue - calculationBase);
    }
    return pointsSpent;
  }

  public int spentOnCheap() {
    int points = 0;
    for (IBasicTrait trait : traits) {
      if (trait.getStatusManager().getStatus().isCheap()) {
        int creationValue = trait.getCreationModel().getValue();
        points += Math.max(0, creationValue - calculationBase);
      }
    }
    return points;
  }

  public int spentOnCheapInExcessOfCredit(int groupCredit) {
    int pointSpentWithoutCredit = Math.max(spentTotally() - groupCredit, 0);
    return Math.min(spentOnCheap(), pointSpentWithoutCredit);
  }

  public int cheaplySpentAsPartOfCredit(int credit) {
    return spentOnCheap() - spentOnCheapInExcessOfCredit(credit);
  }

  public int expensivlySpentAsPartOfCredit(int credit) {
    int creditSpent = Math.min(credit, spentTotally());
    return creditSpent - cheaplySpentAsPartOfCredit(credit);
  }
}