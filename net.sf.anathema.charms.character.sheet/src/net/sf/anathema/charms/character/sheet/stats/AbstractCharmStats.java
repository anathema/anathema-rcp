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
    //TODO Case 346: Hier muss die Dauer aus dem DisplayCharm (getMagic) abgefragt und internationalisiert zurückgegeben werden.
    return null;
  }

  public String getSourceString() {
    return getMagic().getAllSources();
  }

  public String getDetails() {
    return getMagic().getKeywords();
  }
}