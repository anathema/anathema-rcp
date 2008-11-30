package net.sf.anathema.charms.character.freebies;

public class UnrestrictedFreebiesCount implements ICount {

  private final ICount charmCount;
  private final int cheapFreebies;
  private final int credit;

  public UnrestrictedFreebiesCount(ICount charmCount, int cheapFreebies, int credit) {
    this.charmCount = charmCount;
    this.cheapFreebies = cheapFreebies;
    this.credit = credit;
  }

  @Override
  public int count() {
    return Math.min(charmCount.count() - cheapFreebies, credit);
  }
}