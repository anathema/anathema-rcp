package net.sf.anathema.character.sheet.equipment.weapons.groups;

import net.sf.anathema.character.sheet.equipment.weapons.stats.IEquipmentStats;
import net.sf.anathema.character.sheet.stats.AbstractNameStatsGroup;
import net.sf.anathema.character.sheet.stats.IStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public final class EquipmentNameStatsGroup<T extends IEquipmentStats> extends AbstractNameStatsGroup<T> implements
    IStatsGroup<T> {

  @Override
  public void addContent(PdfPTable table, Font font, T stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, "")); //$NON-NLS-1$
    }
    else {
      String name = stats.getName();
      // TODO Internationalisierung von Waffen hier berücksichtigen
      table.addCell(createTextCell(font, name));
    }
  }

  @Override
  public String getTitle() {
    return "Name";
  }
}