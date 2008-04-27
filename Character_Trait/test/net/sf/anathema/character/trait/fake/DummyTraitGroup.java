package net.sf.anathema.character.trait.fake;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.trait.group.ITraitGroup;

public class DummyTraitGroup implements ITraitGroup {

  private final String id;
  private final List<String> traitIds = new ArrayList<String>();

  public DummyTraitGroup(String id) {
    this.id = id;
  }

  @Override
  public String[] getTraitIds() {
    return traitIds.toArray(new String[traitIds.size()]);
  }

  @Override
  public String getId() {
    return id;
  }

  public void addTraitId(String traitId) {
    traitIds.add(traitId);
  }

  @Override
  public String getLabel() {
    return null;
  }
}