package net.sf.anathema.character.sheet.socialcombat.groups;

import net.sf.anathema.character.sheet.socialcombat.ISocialCombatStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class SocialSpeedStatsGroup extends AbstractValueStatsGroup<ISocialCombatStats> {

  @Override
  public String getTitle() {
    return "Speed";
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    table.addCell(FinalValueCell.CreateForValue(font, stats.getSpeed()));
  }

  public int getColumnCount() {
    return 1;
  }
}