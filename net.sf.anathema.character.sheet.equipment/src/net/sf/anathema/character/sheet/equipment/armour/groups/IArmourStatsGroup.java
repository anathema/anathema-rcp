package net.sf.anathema.character.sheet.equipment.armour.groups;

import net.sf.anathema.character.sheet.equipment.stats.IArmourStats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public interface IArmourStatsGroup {

  public void addTotal(PdfPTable table, Font font, IArmourStats armour);
}