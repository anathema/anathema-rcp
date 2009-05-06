package net.sf.anathema.character.sheet.socialcombat;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.socialcombat.attack.InvestigationSocialAttack;
import net.sf.anathema.character.sheet.socialcombat.attack.PerformanceSocialAttack;
import net.sf.anathema.character.sheet.socialcombat.attack.PresenceSocialAttack;
import net.sf.anathema.character.sheet.socialcombat.groups.DeceptionStatsGroup;
import net.sf.anathema.character.sheet.socialcombat.groups.HonestyStatsGroup;
import net.sf.anathema.character.sheet.socialcombat.groups.SocialCombatNameStatsGroup;
import net.sf.anathema.character.sheet.socialcombat.groups.SocialRateStatsGroup;
import net.sf.anathema.character.sheet.socialcombat.groups.SocialSpeedStatsGroup;
import net.sf.anathema.character.sheet.stats.IStatsGroup;
import net.sf.anathema.character.sheet.table.AbstractFixedLineStatsTableEncoder;

import com.lowagie.text.pdf.BaseFont;

public class SocialCombatStatsTableEncoder extends AbstractFixedLineStatsTableEncoder<ISocialCombatStats> {

  public SocialCombatStatsTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<ISocialCombatStats>[] createStatsGroups(ICharacter character) {
    return new IStatsGroup[] {
        new SocialCombatNameStatsGroup(),
        new SocialSpeedStatsGroup(),
        new HonestyStatsGroup(),
        new DeceptionStatsGroup(),
        new SocialRateStatsGroup() };
  }

  @Override
  protected int getLineCount() {
    return 3;
  }

  @Override
  protected ISocialCombatStats[] getPrintStats(ICharacter character) {
    return new ISocialCombatStats[] {
        new PresenceSocialAttack(character),
        new PerformanceSocialAttack(character),
        new InvestigationSocialAttack(character) };
  }
}