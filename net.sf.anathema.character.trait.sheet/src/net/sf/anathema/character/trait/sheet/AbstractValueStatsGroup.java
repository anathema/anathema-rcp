package net.sf.anathema.character.trait.sheet;

import net.sf.anathema.character.sheet.stats.IStats;
import net.sf.anathema.character.sheet.stats.IStatsGroup;
import net.sf.anathema.character.sheet.table.TableEncodingUtilities;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;

public abstract class AbstractValueStatsGroup<T extends IStats> implements IStatsGroup<T> {

  public final Float[] getColumnWeights() {
    return TableEncodingUtilities.createStandardColumnWidths(getColumnCount(), 1);
  }

  @Deprecated
  protected final PdfPCell createFinalValueCell(Font font) {
    return FinalValueCell.CreateEmpty(font);
  }

  @Deprecated
  protected final PdfPCell createFinalValueCell(Font font, Integer value) {
    return FinalValueCell.CreateForValue(font, value);
  }

  @Deprecated
  protected final PdfPCell createFinalValueCell(Font font, String text) {
    return FinalValueCell.CreateForText(font, text);
  }

  @Deprecated
  protected final PdfPCell createEmptyValueCell(Font font) {
    return IntermediaryValueCell.CreateEmpty(font);
  }

  @Deprecated
  protected final PdfPCell createEquipmentValueCell(Font font, Integer value) {
    return IntermediaryValueCell.CreateForValue(font, value);
  }
}