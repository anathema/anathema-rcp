package net.sf.anathema.charms.character.sheet.stats;

import net.sf.anathema.charms.character.sheet.group.AbstractTextStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class MagicTypeStatsGroup extends AbstractTextStatsGroup<IMagicStats> {


  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    String text = stats == null ? null : stats.getType();
    table.addCell(createTextCell(font, text));
  }

  public Float[] getColumnWeights() {
    return new Float[] { 2.5f };
  }

  public String getTitle() {
    return Messages.MagicTypeStatsGroup_Title;
  }
}