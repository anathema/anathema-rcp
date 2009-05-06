package net.sf.anathema.character.sheet.equipment.armour.groups;

import net.sf.anathema.character.sheet.equipment.stats.HealthType;
import net.sf.anathema.character.sheet.equipment.stats.IArmourStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;
import net.sf.anathema.character.trait.sheet.IntermediaryValueCell;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class SoakArmourStatsGroup extends AbstractValueStatsGroup<IArmourStats> implements IArmourStatsGroup {

  private String valuePrefix;

  @Override
  public String getTitle() {
    return "Soak (B/L/A)";
  }

  public void addContent(PdfPTable table, Font font, IArmourStats armour) {
    if (armour == null) {
      table.addCell(IntermediaryValueCell.CreateEmpty(font));
      table.addCell(IntermediaryValueCell.CreateEmpty(font));
      table.addCell(IntermediaryValueCell.CreateEmpty(font));
    }
    else {
      table.addCell(createValueCell(font, armour, HealthType.Bashing));
      table.addCell(createValueCell(font, armour, HealthType.Lethal));
      table.addCell(createValueCell(font, armour, HealthType.Aggravated));
    }
    valuePrefix = "+"; //$NON-NLS-1$
  }

  private PdfPCell createValueCell(Font font, IArmourStats armour, HealthType healthType) {
    return IntermediaryValueCell.CreateForValue(font, armour.getSoak(healthType), valuePrefix, valuePrefix);
  }

  public void addTotal(PdfPTable table, Font font, IArmourStats armour) {
    table.addCell(FinalValueCell.CreateForValue(font, armour.getSoak(HealthType.Bashing)));
    table.addCell(FinalValueCell.CreateForValue(font, armour.getSoak(HealthType.Lethal)));
    table.addCell(FinalValueCell.CreateForValue(font, armour.getSoak(HealthType.Aggravated)));
  }

  public int getColumnCount() {
    return 3;
  }
}