package net.sf.anathema.character.sheet.socialcombat.groups;

import net.sf.anathema.character.sheet.socialcombat.ISocialCombatStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class DeceptionStatsGroup extends AbstractValueStatsGroup<ISocialCombatStats> {

  @Override
  public String getTitle() {
    return "Deception\nAttack/MDV";
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    if (stats == null) {
      table.addCell(createFinalValueCell(font));
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, stats.getDeceptionAttackValue()));
      table.addCell(createFinalValueCell(font, stats.getDeceptionMDV()));
    }
  }

  public int getColumnCount() {
    return 2;
  }
}