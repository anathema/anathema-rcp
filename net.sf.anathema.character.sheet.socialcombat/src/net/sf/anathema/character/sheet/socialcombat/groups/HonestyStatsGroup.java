package net.sf.anathema.character.sheet.socialcombat.groups;

import net.sf.anathema.character.sheet.socialcombat.ISocialCombatStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class HonestyStatsGroup extends AbstractValueStatsGroup<ISocialCombatStats> {

  @Override
  public String getTitle() {
    return "Honesty\nAttack/MDV";
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    table.addCell(FinalValueCell.CreateForValue(font, stats.getHonestyAttackValue()));
    table.addCell(FinalValueCell.CreateForValue(font, stats.getHonestyMDV()));
  }

  public int getColumnCount() {
    return 2;
  }
}