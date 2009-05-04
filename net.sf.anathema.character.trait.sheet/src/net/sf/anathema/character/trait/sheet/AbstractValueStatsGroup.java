package net.sf.anathema.character.trait.sheet;

import java.awt.Color;

import net.sf.anathema.character.sheet.stats.IStats;
import net.sf.anathema.character.sheet.stats.IStatsGroup;
import net.sf.anathema.character.sheet.table.TableEncodingUtilities;
import net.sf.anathema.character.trait.display.IDisplayTrait;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;

public abstract class AbstractValueStatsGroup<T extends IStats> implements IStatsGroup<T> {

  public Float[] getColumnWeights() {
    return TableEncodingUtilities.createStandardColumnWeights(getColumnCount());
  }

  protected final PdfPCell createFinalValueCell(Font font) {
    return createContentCellTable(Color.BLACK, " ", font, 0.75f, true); //$NON-NLS-1$
  }

  protected final PdfPCell createFinalValueCell(Font font, Integer value) {
    return createFinalValueCell(font, value != null ? value.toString() : null);
  }

  protected final PdfPCell createFinalValueCell(Font font, String text) {
    String content = text != null ? text : " "; //$NON-NLS-1$
    return createContentCellTable(Color.BLACK, content, font, 0.75f, text != null);
  }

  protected final PdfPCell createFinalValueCell(Font font, String text, int alignment) {
    boolean enabled = text != null;
    String content = enabled ? text : " "; //$NON-NLS-1$
    return createContentCellTable(Color.BLACK, content, font, 0.75f, alignment, enabled);
  }

  protected final PdfPCell createEmptyValueCell(Font font) {
    return createContentCellTable(Color.GRAY, " ", font, 0.5f, true); //$NON-NLS-1$
  }

  protected final PdfPCell createEquipmentValueCell(Font font, Integer value) {
    String text = getStatsValueString(value);
    return createContentCellTable(Color.GRAY, text, font, 0.5f, value != null);
  }

  private final String getStatsValueString(Integer value) {
    if (value == null) {
      return " "; //$NON-NLS-1$
    }
    StringBuilder stringBuilder = new StringBuilder(value.toString());
    if (value == 0) {
      stringBuilder.insert(0, getZeroPrefix());
    }
    if (value > 0) {
      stringBuilder.insert(0, getPositivePrefix());
    }
    return stringBuilder.toString();
  }

  protected String getPositivePrefix() {
    return "+"; //$NON-NLS-1$
  }

  protected String getZeroPrefix() {
    return "+"; //$NON-NLS-1$
  }

  private final PdfPCell createContentCellTable(
      Color borderColor,
      String text,
      Font font,
      float borderWidth,
      boolean enabled) {
    return TableEncodingUtilities.createContentCellTable(
        borderColor,
        text,
        font,
        borderWidth,
        Rectangle.BOX,
        Element.ALIGN_RIGHT,
        enabled);
  }

  private final PdfPCell createContentCellTable(
      Color borderColor,
      String text,
      Font font,
      float borderWidth,
      int alignment,
      boolean enabled) {
    return TableEncodingUtilities.createContentCellTable(
        borderColor,
        text,
        font,
        borderWidth,
        Rectangle.BOX,
        alignment,
        enabled);
  }

  protected final int calculateFinalValue(final int weaponValue, IDisplayTrait... traits) {
    int totalValue = weaponValue;
    for (IDisplayTrait trait : traits) {
      totalValue += trait.getValue();
    }
    return totalValue;
  }
}