package net.sf.anathema.character.trait.sheet;

import java.awt.Color;

import net.sf.anathema.character.sheet.table.ContentCellBuilder;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;

public class IntermediaryValueCell {

  public static PdfPCell CreateEmpty(Font font) {
    return CreateForValue(font, null);
  }

  public static PdfPCell CreateForValue(Font font, Integer value) {
    String content = getStatsValueString(value);
    return CreateForText(font, content);
  }

  private static final String getStatsValueString(Integer value) {
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

  private static String getPositivePrefix() {
    return "+"; //$NON-NLS-1$
  }

  private static String getZeroPrefix() {
    return "+"; //$NON-NLS-1$
  }

  public static PdfPCell CreateForText(Font font, String content) {
    ContentCellBuilder cellBuilder = new ContentCellBuilder(font);
    cellBuilder.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cellBuilder.setBorder(Color.GRAY, 0.5f, Rectangle.BOX);
    cellBuilder.setText(content);
    if (content == null) {
      cellBuilder.disable();
    }
    return cellBuilder.create();
  }
}