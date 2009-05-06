package net.sf.anathema.character.sheet.equipment.weapons.groups;

import net.sf.anathema.character.sheet.equipment.weapons.stats.IWeaponStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class SpeedWeaponStatsGroup extends AbstractValueStatsGroup<IWeaponStats> {

  public int getColumnCount() {
    return 1;
  }

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(FinalValueCell.CreateEmpty(font));
    }
    else {
      table.addCell(FinalValueCell.CreateForValue(font, weapon.getSpeed()));
    }
  }

  @Override
  public String getTitle() {
    return "Speed";
  }
}