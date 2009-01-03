package net.sf.anathema.charms.character.sheet.stats;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.charms.character.sheet.group.AbstractTextStatsGroup;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class MagicDetailsStatsGroup extends AbstractTextStatsGroup<IMagicStats> {

  public void addContent(PdfPTable table, Font font, IMagicStats stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, null));
    }
    else {
      String details = stats.getDetails();
      if (StringUtilities.isNullOrEmpty(details)) {
        details = "-";
      }
      table.addCell(createTextCell(font, details));
    }
  }

  public Float[] getColumnWeights() {
    return new Float[] { 7f };
  }

  public String getTitle() {
    return "Details";
  }
}