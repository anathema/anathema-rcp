package net.sf.anathema.character.trait.group;

public class TraitGroup implements ITraitGroup {

  private final String[] traitIds;
  private final String id;

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
}