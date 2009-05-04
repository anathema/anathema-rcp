package net.sf.anathema.character.sheet.anima;

import static net.sf.anathema.character.sheet.common.IEncodeContext.*;

import java.awt.Color;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.anima.columns.BannerFlareColumn;
import net.sf.anathema.character.sheet.anima.columns.MotesColumn;
import net.sf.anathema.character.sheet.anima.columns.StealthColumn;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class AnimaTableEncoder extends AbstractTableEncoder {

  private final Font headerFont;
  private final Font font;

  public AnimaTableEncoder(float fontSize) {
    this.headerFont = new Font(BASEFONT, fontSize, Font.ITALIC, Color.BLACK);
    this.font = new Font(BASEFONT, fontSize, Font.NORMAL, Color.BLACK);
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, ICharacter character, Bounds bounds) {
    IAnimaColumn[] columns = getColumns();
    float[] columnWidths = getColumWidths(columns);
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    for (IAnimaColumn column : columns) {
      ColumnDescriptor descriptor = column.getDescriptor();
      table.addCell(createHeaderCell(descriptor.getHeaderTitle()));
    }
    for (int index = 0; index < 5; index++) {
      for (IAnimaColumn column : columns) {
        String cellContent = column.getContent(index, character);
        PdfPCell cell = createContentCell(cellContent);
        table.addCell(cell);
      }
    }
    return table;
  }

  private float[] getColumWidths(IAnimaColumn[] columns) {
    float[] widths = new float[columns.length];
    for (int index = 0; index < widths.length; index++) {
      widths[index] = columns[index].getDescriptor().getWidthPart();
    }
    return widths;
  }

  protected IAnimaColumn[] getColumns() {
    return new IAnimaColumn[] { new MotesColumn(), new BannerFlareColumn(), new StealthColumn() };
  }

  protected final PdfPCell createContentCell(String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, font));
    cell.setPaddingTop(1);
    cell.setPaddingBottom(2);
    return cell;
  }

  private PdfPCell createHeaderCell(String text) {
    PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
    cell.setBorder(Rectangle.BOTTOM);
    return cell;
  }
}