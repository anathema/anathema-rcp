package net.sf.anathema.character.sheet.equipment.weapons.groups;

import net.sf.anathema.character.sheet.equipment.weapons.stats.IWeaponStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class RangeWeaponStatsGroup extends AbstractValueStatsGroup<IWeaponStats> {

  public int getColumnCount() {
    return 1;
  }

  @Override
  public String getTitle() {
    return "Range";
  }

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(createFinalValueCell(font));
    }
    else {
      table.addCell(createFinalValueCell(font, weapon.getRange()));
    }
  }
}