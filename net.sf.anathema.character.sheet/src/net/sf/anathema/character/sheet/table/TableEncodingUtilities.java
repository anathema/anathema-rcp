package net.sf.anathema.character.sheet.table;

import java.awt.Color;

import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

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
    PdfPCell innerCell = new PdfPCell(new Phrase(text, font));
    innerCell.setBorderColor(borderColor);
    innerCell.setBorderWidth(borderWidth);
    innerCell.setBorder(border);
    innerCell.setHorizontalAlignment(alignment);
    innerCell.setPaddingTop(0.5f);
    if (border != Rectangle.BOX) {
      innerCell.setPaddingLeft(0);
      innerCell.setPaddingRight(0);
    }
    if (!enabled) {
      innerCell.setBackgroundColor(Color.LIGHT_GRAY);
    }

    PdfPTable cellTable = new PdfPTable(1);
    cellTable.setWidthPercentage(100);
    cellTable.addCell(innerCell);

    PdfPCell outerCell = new PdfPCell();
    outerCell.addElement(cellTable);
    outerCell.setBorder(Rectangle.NO_BORDER);
    outerCell.setPadding(1);
    return outerCell;
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