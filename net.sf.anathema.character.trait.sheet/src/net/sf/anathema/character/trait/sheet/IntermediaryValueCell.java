package net.sf.anathema.character.trait.sheet;

import java.awt.Color;

import net.sf.anathema.character.sheet.table.ContentCellBuilder;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;

public class IntermediaryValueCell {

  public static final String POSITIVE_PREFIX = "+"; //$NON-NLS-1$
  public static final String ZERO_PREFIX = "+"; //$NON-NLS-1$

  public static PdfPCell CreateEmpty(Font font) {
    return CreateForValue(font, null);
  }

  public static PdfPCell CreateForValue(Font font, Integer value) {
    return CreateForValue(font, value, POSITIVE_PREFIX, ZERO_PREFIX);
  }

  public static PdfPCell CreateForValue(Font font, Integer value, String positivePrefix, String zeroPrefix) {
    String content = getStatsValueString(value, positivePrefix, zeroPrefix);
    return CreateForText(font, content);
  }

  private static final String getStatsValueString(Integer value, String positivePrefix, String zeroPrefix) {
    if (value == null) {
      return " "; //$NON-NLS-1$
    }
    StringBuilder stringBuilder = new StringBuilder(value.toString());
    if (value == 0) {
      stringBuilder.insert(0, zeroPrefix);
    }
    if (value > 0) {
      stringBuilder.insert(0, positivePrefix);
    }
    return stringBuilder.toString();
  }

  private static PdfPCell CreateForText(Font font, String content) {
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