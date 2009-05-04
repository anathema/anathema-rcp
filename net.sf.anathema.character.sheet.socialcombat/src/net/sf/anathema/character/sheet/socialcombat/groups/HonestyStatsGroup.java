package net.sf.anathema.character.sheet.socialcombat.groups;

import net.sf.anathema.character.sheet.socialcombat.ISocialCombatStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class HonestyStatsGroup extends AbstractValueStatsGroup<ISocialCombatStats> {

  @Override
  public String getTitle() {
    return "Honesty\nAttack/MDV";
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, stats.getHonestyAttackValue()));
      table.addCell(createFinalValueCell(font, stats.getHonestyMDV()));
    }
  }

  public int getColumnCount() {
    return 2;
  }
}