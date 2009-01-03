package net.sf.anathema.charms.character.sheet.group;

import net.sf.anathema.charms.character.sheet.stats.IStats;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public interface IStatsGroup<T extends IStats> {

  public int getColumnCount();

  public String getTitle();

  public Float[] getColumnWeights();

  public void addContent(PdfPTable table, Font font, T stats);
}