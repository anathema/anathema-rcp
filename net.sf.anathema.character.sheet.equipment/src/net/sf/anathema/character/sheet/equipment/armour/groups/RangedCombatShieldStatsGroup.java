package net.sf.anathema.character.sheet.equipment.armour.groups;

import net.sf.anathema.character.sheet.equipment.stats.IShieldStats;
import net.sf.anathema.character.sheet.table.TableEncodingUtilities;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class RangedCombatShieldStatsGroup extends AbstractValueStatsGroup<IShieldStats> {

  @Override
  public String getTitle() {
    return null;
  }

  public int getColumnCount() {
    return 2;
  }

  public void addContent(PdfPTable table, Font font, IShieldStats shield) {
    PdfPCell cell = TableEncodingUtilities.createContentCellTable(
        null,
        "Range",
        font,
        0f,
        Rectangle.NO_BORDER,
        Element.ALIGN_LEFT);
    table.addCell(cell);
    if (shield == null) {
      table.addCell(FinalValueCell.CreateEmpty(font));
    }
    else {
      table.addCell(FinalValueCell.CreateForValue(font, shield.getRangedCombatBonus()));
    }
  }
}