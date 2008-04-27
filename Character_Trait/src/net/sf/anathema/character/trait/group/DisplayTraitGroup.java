package net.sf.anathema.character.trait.group;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.display.IDisplayTrait;

public class DisplayTraitGroup<T extends IDisplayTrait> implements IDisplayTraitGroup<T> {

  private final List<T> traits = new ArrayList<T>();
  private final String id;
  private final String label;

  public DisplayTraitGroup(String id, String label) {
    this.id = id;
    this.label = label;
  }

  public void addTrait(T trait) {
    traits.add(trait);
  }

  @Override
  public Iterable<T> getTraits() {
    return traits;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getLabel() {
    return label;
  }
}