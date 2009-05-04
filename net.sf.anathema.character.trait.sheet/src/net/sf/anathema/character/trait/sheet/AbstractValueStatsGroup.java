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

  @Deprecated
  protected final PdfPCell createFinalValueCell(Font font) {
    return FinalValueCell.CreateEmpty(font);
  }

  @Deprecated
  protected final PdfPCell createFinalValueCell(Font font, Integer value) {
    return FinalValueCell.CreateForValue(font, value);
  }

  @Deprecated
  protected final PdfPCell createFinalValueCell(Font font, String text, int alignment) {
    PdfPCell cell = FinalValueCell.CreateForText(font, text);
    cell.setHorizontalAlignment(alignment);
    return cell;
  }

  @Deprecated
  protected final PdfPCell createFinalValueCell(Font font, String text) {
    return FinalValueCell.CreateForText(font, text);
  }

  protected final PdfPCell createEmptyValueCell(Font font) {
    return createContentCellTable(Color.GRAY, " ", font, 0.5f, true);
  }

  protected final PdfPCell createEquipmentValueCell(Font font, Integer value) {
    String text = getStatsValueString(value);
    return createContentCellTable(Color.GRAY, text, font, 0.5f, value != null);
  }

  private final String getStatsValueString(Integer value) {
    if (value == null) {
      return " ";
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
    return "+";
  }

  protected String getZeroPrefix() {
    return "+";
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