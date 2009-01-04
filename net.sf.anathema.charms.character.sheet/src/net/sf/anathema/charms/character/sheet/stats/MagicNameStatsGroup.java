package net.sf.anathema.charms.character.sheet.stats;

import net.sf.anathema.charms.character.sheet.group.AbstractTextStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class MagicNameStatsGroup extends AbstractTextStatsGroup<IMagicStats> {


  public Float[] getColumnWeights() {
    return new Float[] { 6.0f };
  }

  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, "")); //$NON-NLS-1$
    }
    else {
      table.addCell(createTextCell(font, stats.getName()));
    }
  }

  public String getTitle() {
    return Messages.MagicNameStatsGroup_Title;
  }
}