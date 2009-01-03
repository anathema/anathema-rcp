package net.sf.anathema.charms.character.sheet.stats;

import net.sf.anathema.charms.display.DisplayCharm;

public abstract class AbstractMagicStats implements IMagicStats {

  private final DisplayCharm magic;

  public AbstractMagicStats(DisplayCharm magic) {
    this.magic = magic;
  }

  public String getCostString() {
    return magic.getCost();
  }

  protected final DisplayCharm getMagic() {
    return magic;
  }
}