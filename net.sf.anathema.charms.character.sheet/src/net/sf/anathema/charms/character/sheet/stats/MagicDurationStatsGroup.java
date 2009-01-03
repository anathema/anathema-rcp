package net.sf.anathema.charms.character.sheet.stats;

import net.sf.anathema.charms.character.sheet.group.AbstractTextStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class MagicDurationStatsGroup extends AbstractTextStatsGroup<IMagicStats> {


  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    String text = stats == null ? null : stats.getDurationString();
    table.addCell(createTextCell(font, text));
  }

  public Float[] getColumnWeights() {
    return new Float[] { 3f };
  }

  public String getTitle() {
    return "Duration";
  }
}