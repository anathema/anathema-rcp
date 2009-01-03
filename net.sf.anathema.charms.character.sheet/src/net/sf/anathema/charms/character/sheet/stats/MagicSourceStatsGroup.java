package net.sf.anathema.charms.character.sheet.stats;

import net.sf.anathema.charms.character.sheet.group.AbstractTextStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class MagicSourceStatsGroup extends AbstractTextStatsGroup<IMagicStats> {


  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    String text = stats == null ? null : stats.getSourceString();
    table.addCell(createTextCell(font, text));
  }

  public Float[] getColumnWeights() {
    return new Float[] { 2.0f };
  }

  public String getTitle() {
    return "Source";
  }
}