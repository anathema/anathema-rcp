package net.sf.anathema.character.sheet.socialcombat.groups;

import net.sf.anathema.character.sheet.socialcombat.ISocialCombatStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class DeceptionStatsGroup extends AbstractValueStatsGroup<ISocialCombatStats> {

  @Override
  public String getTitle() {
    return "Deception\nAttack/MDV";
  }

  public void addContent(PdfPTable table, Font font, ISocialCombatStats stats) {
    table.addCell(FinalValueCell.CreateForValue(font, stats.getDeceptionAttackValue()));
    table.addCell(FinalValueCell.CreateForValue(font, stats.getDeceptionMDV()));
  }

  public int getColumnCount() {
    return 2;
  }
}