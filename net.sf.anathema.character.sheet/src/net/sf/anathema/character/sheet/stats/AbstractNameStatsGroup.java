package net.sf.anathema.character.sheet.stats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractNameStatsGroup<T extends IStats> extends AbstractTextStatsGroup<T> {

  public Float[] getColumnWeights() {
    return new Float[] { new Float(6) };
  }

  public void addContent(PdfPTable table, Font font, T stats) {
    if (stats == null) {
      table.addCell(createTextCell(font, "")); //$NON-NLS-1$
    }
    else {
      table.addCell(createTextCell(font, stats.getName()));
    }
  }
}