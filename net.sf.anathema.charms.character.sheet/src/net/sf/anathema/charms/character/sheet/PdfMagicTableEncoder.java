package net.sf.anathema.charms.character.sheet;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.charms.character.sheet.group.IStatsGroup;
import net.sf.anathema.charms.character.sheet.stats.IMagicStats;
import net.sf.anathema.charms.character.sheet.stats.MagicCostStatsGroup;
import net.sf.anathema.charms.character.sheet.stats.MagicDetailsStatsGroup;
import net.sf.anathema.charms.character.sheet.stats.MagicDurationStatsGroup;
import net.sf.anathema.charms.character.sheet.stats.MagicNameStatsGroup;
import net.sf.anathema.charms.character.sheet.stats.MagicSourceStatsGroup;
import net.sf.anathema.charms.character.sheet.stats.MagicTypeStatsGroup;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;

public class PdfMagicTableEncoder extends AbstractStatsTableEncoder<IMagicStats> {

  private List<IMagicStats> printStats = new ArrayList<IMagicStats>();

  public PdfMagicTableEncoder(BaseFont baseFont, List<IMagicStats> printStats) {
    super(baseFont);
    this.printStats = printStats;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IMagicStats>[] createStatsGroups() {
    return new IStatsGroup[] {
        new MagicNameStatsGroup(),
        new MagicCostStatsGroup(),
        new MagicTypeStatsGroup(),
        new MagicDurationStatsGroup(),
        new MagicDetailsStatsGroup(),
        new MagicSourceStatsGroup() };
  }

  @Override
  protected void encodeContent(PdfPTable table, Bounds bounds) {
    float heightLimit = bounds.height - 3;
    IStatsGroup<IMagicStats>[] groups = createStatsGroups();
    boolean encodeLine = true;
    String groupName = null;
    for (IMagicStats stats : printStats.toArray(new IMagicStats[printStats.size()])) {
      String newGroupName = stats.getGroupName();
      if (!ObjectUtilities.equals(groupName, newGroupName)) {
        groupName = newGroupName;
        encodeSectionLine(table, groupName);
        encodeLine = table.getTotalHeight() < heightLimit;
        if (!encodeLine) {
          table.deleteLastRow();
          return;
        }
      }
      if (encodeLine) {
        encodeContentLine(table, groups, stats);
        encodeLine = table.getTotalHeight() < heightLimit;
        if (!encodeLine) {
          table.deleteLastRow();
          return;
        }
        printStats.remove(stats);
      }
    }
    while (encodeLine) {
      encodeContentLine(table, groups, null);
      encodeLine = table.getTotalHeight() < heightLimit;
    }
    table.deleteLastRow();
  }
}