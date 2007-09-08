package net.sf.anathema.character.attributes.points;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
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

  public int spentOnFavored() {
    int points = 0;
    for (IBasicTrait trait : traits) {
      if (trait.getFavoredModel().getValue()) {
        int creationValue = trait.getCreationModel().getValue();
        points += Math.max(0, creationValue - calculationBase);
      }
    }
    return points;
  }
}