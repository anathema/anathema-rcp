package net.sf.anathema.charms.character.freebies;

public class CheapFreebiesCount implements ICount {

  private final ICount cheapCharmCount;
  private final int credit;

  public CheapFreebiesCount(ICount cheapCharmCount, int credit) {
    this.cheapCharmCount = cheapCharmCount;
    this.credit = credit;
  }

  @Override
  public int count() {
    return Math.min(cheapCharmCount.count(), credit);
  }
}