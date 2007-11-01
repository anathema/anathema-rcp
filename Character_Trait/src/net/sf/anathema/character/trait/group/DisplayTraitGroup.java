package net.sf.anathema.character.trait.group;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.IInteractiveTrait;

public class DisplayTraitGroup implements IDisplayTraitGroup {

  private final List<IInteractiveTrait> traits = new ArrayList<IInteractiveTrait>();
  private final String id;
  
  public DisplayTraitGroup(String id) {
    this.id = id;
  }

  public void addTrait(IInteractiveTrait trait) {
    traits.add(trait);
  }

  @Override
  public Iterable<IInteractiveTrait> getTraits() {
    return traits;
  }

  @Override
  public String getId() {
    return id;
  }
}