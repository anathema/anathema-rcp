package net.sf.anathema.character.sheet.table;

import java.awt.Color;

import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;

public class TableEncodingUtilities {

  public static final float FONT_SIZE = IVoidStateFormatConstants.FONT_SIZE - 0.5f;

  public static Float[] createStandardColumnWeights(int columnCount) {
    return createStandardColumnWidths(columnCount, 1);
  }

  public static Float[] createStandardColumnWidths(int columnCount, final float value) {
    Float[] columnWeights = new Float[columnCount];
    for (int index = 0; index < columnWeights.length; index++) {
      columnWeights[index] = new Float(value);
    }
    return columnWeights;
  }

  public static final PdfPCell createContentCellTable(
      Color borderColor,
      String text,
      Font font,
      float borderWidth,
      int border,
      int alignment) {
    return createContentCellTable(borderColor, text, font, borderWidth, border, alignment, true);
  }

  public static final PdfPCell createContentCellTable(
      Color borderColor,
      String text,
      Font font,
      float borderWidth,
      int border,
      int alignment,
      boolean enabled) {
    ContentCellBuilder cellBuilder = new ContentCellBuilder(font);
    cellBuilder.setText(text);
    cellBuilder.setHorizontalAlignment(alignment);
    cellBuilder.setBorder(borderColor, borderWidth, border);
    if (!enabled) {
      cellBuilder.disable();
    }
    return cellBuilder.create();
  }

  public static Font createFont(BaseFont baseFont) {
    return new Font(baseFont, FONT_SIZE, Font.NORMAL, Color.BLACK);
  }

  public static Font createHeaderFont(BaseFont baseFont) {
    return new Font(baseFont, IVoidStateFormatConstants.FONT_SIZE - 1, Font.NORMAL, Color.BLACK);
  }

  public static Font createCommentFont(BaseFont baseFont) {
    return new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE, Font.NORMAL, Color.BLACK);
  }
}