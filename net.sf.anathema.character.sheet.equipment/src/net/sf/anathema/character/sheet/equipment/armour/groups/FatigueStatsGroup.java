package net.sf.anathema.character.sheet.equipment.armour.groups;

import static net.sf.anathema.character.trait.sheet.IntermediaryValueCell.*;
import net.sf.anathema.character.sheet.equipment.stats.IArmourStats;
import net.sf.anathema.character.sheet.equipment.stats.IDefensiveStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class FatigueStatsGroup extends AbstractValueStatsGroup<IDefensiveStats> implements IArmourStatsGroup {

  @Override
  public String getTitle() {
    return "Fatigue";
  }

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font, IDefensiveStats stats) {
    if (stats == null) {
      table.addCell(CreateEmpty(font));
    }
    else {
      table.addCell(CreateForValue(font, stats.getFatigue(), "", ZERO_PREFIX));
    }
  }

  public void addTotal(PdfPTable table, Font font, IArmourStats totalArmour) {
    table.addCell(FinalValueCell.CreateForValue(font, totalArmour.getFatigue()));
  }
}