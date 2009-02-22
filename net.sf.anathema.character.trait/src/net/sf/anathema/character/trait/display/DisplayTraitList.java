package net.sf.anathema.character.trait.display;

import java.util.Iterator;
import java.util.List;

import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.lib.util.Identificate;

public class DisplayTraitList<T extends IDisplayTrait> implements Iterable<IDisplayTraitGroup<T>> {

  private final List<IDisplayTraitGroup<T>> groups;

  public DisplayTraitList(List<IDisplayTraitGroup<T>> groups) {
    this.groups = groups;
  }

  public T getTrait(Identificate type) {
    for (IDisplayTraitGroup<T> group : groups) {
      for (T trait : group) {
        if (trait.getTraitType().equals(type)) {
          return trait;
        }
      }
    }
    throw new IllegalArgumentException("No trait found for type " + type.getId());
  }

  @Override
  public Iterator<IDisplayTraitGroup<T>> iterator() {
    return groups.iterator();
  }
}