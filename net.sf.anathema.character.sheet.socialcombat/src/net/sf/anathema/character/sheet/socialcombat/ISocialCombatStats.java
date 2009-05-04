package net.sf.anathema.character.sheet.socialcombat;

import net.sf.anathema.character.sheet.stats.IStats;

public interface ISocialCombatStats extends IStats {

  public int getDeceptionAttackValue();

  public int getDeceptionMDV();

  public int getHonestyAttackValue();

  public int getHonestyMDV();

  public int getRate();

  public int getSpeed();
}