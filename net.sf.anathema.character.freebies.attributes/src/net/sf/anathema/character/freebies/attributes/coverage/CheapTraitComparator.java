package net.sf.anathema.character.freebies.attributes.coverage;

import java.util.Comparator;

import net.sf.anathema.character.trait.collection.ITraitCollectionModel;

public final class CheapTraitComparator implements Comparator<String> {
  private final ITraitCollectionModel collection;

  public CheapTraitComparator(ITraitCollectionModel collection) {
    this.collection = collection;
  }

  @Override
  public int compare(String firstType, String secondType) {
    boolean firstCheap = collection.getTrait(firstType).getStatusManager().getStatus().isCheap();
    boolean secondCheap = collection.getTrait(secondType).getStatusManager().getStatus().isCheap();
    if (firstCheap && secondCheap || !(firstCheap || secondCheap)) {
      return 0;
    }
    return firstCheap ? -1 : 1;
  }
}