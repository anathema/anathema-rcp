package net.sf.anathema.character.sheet.socialcombat.groups;

import net.sf.anathema.character.sheet.socialcombat.ISocialCombatStats;
import net.sf.anathema.character.sheet.stats.AbstractNameStatsGroup;

public class SocialCombatNameStatsGroup extends AbstractNameStatsGroup<ISocialCombatStats> {

  @Override
  public String getTitle() {
    return "Attack";
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[] { new Float(3) };
  }
}