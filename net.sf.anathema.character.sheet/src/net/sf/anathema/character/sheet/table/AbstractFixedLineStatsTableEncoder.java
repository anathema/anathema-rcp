package net.sf.anathema.character.sheet.table;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.stats.IStats;
import net.sf.anathema.character.sheet.stats.IStatsGroup;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractFixedLineStatsTableEncoder<T extends IStats> extends AbstractStatsTableEncoder<T> {

  public AbstractFixedLineStatsTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  @Override
  protected void encodeContent(PdfPTable table, ICharacter character, Bounds bounds) {
    IStatsGroup<T>[] groups = createStatsGroups();
    T[] printStats = getPrintStats(character);
    int line = 0;
    while (line < getLineCount()) {
      T printStat = line < printStats.length ? printStats[line] : null;
      encodeContentLine(table, groups, printStat);
      line++;
    }
  }

  protected abstract int getLineCount();

  protected abstract T[] getPrintStats(ICharacter character);
}