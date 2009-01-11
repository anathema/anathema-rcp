package net.sf.anathema.charms.character.sheet.stats;

import net.sf.anathema.charms.display.DisplayCharm;

public abstract class AbstractCharmStats extends AbstractMagicStats {

  public AbstractCharmStats(DisplayCharm magic) {
    super(magic);
  }

  public String getType() {
    return getMagic().getType();
  }

  public String getDurationString() {
    return getMagic().getDuration();
  }

  public String getSourceString() {
    return getMagic().getAllSources();
  }

  public String getDetails() {
    return getMagic().getKeywords();
  }
}