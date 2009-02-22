package net.sf.anathema.character.trait.display;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import net.sf.anathema.character.trait.group.IDisplayTraitGroup;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DisplayTraitList<T extends IDisplayTrait> implements Iterable<IDisplayTraitGroup<T>> {

  private final List<IDisplayTraitGroup<T>> groups;

  public DisplayTraitList(List<IDisplayTraitGroup<T>> groups) {
    this.groups = groups;
  }

  public T getTrait(String type) {
    return getTrait(new Identificate(type));
  }

  public T getTrait(IIdentificate type) {
    for (IDisplayTraitGroup<T> group : groups) {
      for (T trait : group) {
        if (trait.getTraitType().equals(type)) {
          return trait;
        }
      }
    }
    throw new IllegalArgumentException(MessageFormat.format("No trait found for type {0}.", type.getId()));
  }

  @Override
  public Iterator<IDisplayTraitGroup<T>> iterator() {
    return groups.iterator();
  }
}