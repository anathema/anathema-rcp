package net.sf.anathema.character.attributes;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.IDisplayTrait;

public class TraitGroup implements ITraitGroup {

  private final String[] traitIds;
  private final String id;

  private final List<IDisplayTrait> traits = new ArrayList<IDisplayTrait>();

  public TraitGroup(String id, String... traitIds) {
    this.id = id;
    this.traitIds = traitIds;
  }

  public String[] getTraitIds() {
    return traitIds;
  }

  @Override
  public String getId() {
    return id;
  }

  public void addTrait(IDisplayTrait trait) {
    traits.add(trait);
  }

  @Override
  public Iterable<IDisplayTrait> getTraits() {
    return traits;
  }
}