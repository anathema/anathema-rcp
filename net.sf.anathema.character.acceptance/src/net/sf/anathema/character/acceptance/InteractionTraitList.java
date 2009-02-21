package net.sf.anathema.character.acceptance;

import java.util.List;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.character.trait.interactive.IInteractiveTrait;
import net.sf.anathema.character.trait.status.FavoredStatus;
import net.sf.anathema.lib.util.Identificate;

@SuppressWarnings("nls")
public class InteractionTraitList {

  private final List<IDisplayTraitGroup<IInteractiveTrait>> groups;

  public InteractionTraitList(List<IDisplayTraitGroup<IInteractiveTrait>> groups) {
    this.groups = groups;
  }

  public IInteractiveTrait setTraitFavoredAndReturn(
      ITraitCollectionModel traitCollection,
      int groupIndex,
      int traitIndex) {
    IDisplayTraitGroup<IInteractiveTrait> group = groups.get(groupIndex);
    IInteractiveTrait trait = findTrait(group, traitIndex);
    setFavored(traitCollection, trait);
    return trait;
  }

  private IInteractiveTrait findTrait(IDisplayTraitGroup<IInteractiveTrait> group, int traitIndex) {
    int currentIndex = 0;
    for (IInteractiveTrait trait : group) {
      if (currentIndex == traitIndex) {
        return trait;
      }
      currentIndex++;
    }
    throw new IndexOutOfBoundsException("No trait with index " + traitIndex + " found.");
  }

  private void setFavored(ITraitCollectionModel traitCollection, IInteractiveTrait interactiveTrait) {
    String traitId = interactiveTrait.getTraitType().getId();
    IBasicTrait trait = traitCollection.getTrait(traitId);
    trait.getStatusManager().setStatus(new FavoredStatus());
  }

  public IInteractiveTrait getTrait(Identificate type) {
    for (IDisplayTraitGroup<IInteractiveTrait> group : groups) {
      for (IInteractiveTrait trait : group) {
        if (trait.getTraitType().equals(type)) {
          return trait;
        }
      }
    }
    throw new IllegalArgumentException("No trait found for type " + type.getId());
  }
}