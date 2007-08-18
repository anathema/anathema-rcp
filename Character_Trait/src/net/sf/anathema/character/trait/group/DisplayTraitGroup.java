package net.sf.anathema.character.trait.group;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.IDisplayTrait;

public class DisplayTraitGroup implements IDisplayTraitGroup {

  private final List<IDisplayTrait> traits = new ArrayList<IDisplayTrait>();
  private final String id;
  
  public DisplayTraitGroup(String id) {
    this.id = id;
  }

  public void addTrait(IDisplayTrait trait) {
    traits.add(trait);
  }

  @Override
  public Iterable<IDisplayTrait> getTraits() {
    return traits;
  }

  @Override
  public String getId() {
    return id;
  }
}