package net.sf.anathema.charms.character.freebies;

import java.util.Iterator;

import net.sf.anathema.charms.character.model.ICharmModel;

public class UnrestrictedCharmCount implements ICount {

  private final ICharmModel model;

  public UnrestrictedCharmCount(ICharmModel model) {
    this.model = model;
  }

  @Override
  public int count() {
    int count = 0;
    Iterator<String> iterator = model.getCreationLearnedCharms().iterator();
    while (iterator.hasNext()) {
      iterator.next();
      count++;
    }
    return count;
  }
}