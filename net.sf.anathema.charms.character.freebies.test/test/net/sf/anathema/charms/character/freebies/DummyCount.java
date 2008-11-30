package net.sf.anathema.charms.character.freebies;

public class DummyCount implements ICount {

  private final int count;

  public DummyCount(int count) {
    this.count = count;
  }

  @Override
  public int count() {
    return count;
  }
}