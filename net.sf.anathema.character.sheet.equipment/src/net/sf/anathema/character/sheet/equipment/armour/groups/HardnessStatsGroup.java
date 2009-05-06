package net.sf.anathema.character.sheet.equipment.armour.groups;

import net.sf.anathema.character.sheet.equipment.stats.HealthType;
import net.sf.anathema.character.sheet.equipment.stats.IArmourStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;
import net.sf.anathema.character.trait.sheet.IntermediaryValueCell;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class HardnessStatsGroup extends AbstractValueStatsGroup<IArmourStats> implements IArmourStatsGroup {

  @Override
  public String getTitle() {
    return "Hardness (B/L)";
  }

  public int getColumnCount() {
    return 2;
  }

  public void addContent(PdfPTable table, Font font, IArmourStats armour) {
    if (armour == null) {
      table.addCell(IntermediaryValueCell.CreateEmpty(font));
      table.addCell(IntermediaryValueCell.CreateEmpty(font));
    }
    else {
      table.addCell(createValueCell(font, armour.getHardness(HealthType.Bashing)));
      table.addCell(createValueCell(font, armour.getHardness(HealthType.Lethal)));
    }
  }

  private PdfPCell createValueCell(Font font, Integer hardness) {
    return IntermediaryValueCell.CreateForValue(font, hardness, "", ""); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void addTotal(PdfPTable table, Font font, IArmourStats armour) {
    table.addCell(FinalValueCell.CreateForValue(font, armour.getHardness(HealthType.Bashing)));
    table.addCell(FinalValueCell.CreateForValue(font, armour.getHardness(HealthType.Lethal)));
  }
}