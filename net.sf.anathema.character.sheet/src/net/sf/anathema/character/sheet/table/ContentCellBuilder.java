package net.sf.anathema.character.sheet.table;

import java.awt.Color;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class ContentCellBuilder {

  private final PdfPCell innerCell;
  private final Font font;

  public ContentCellBuilder(Font font) {
    this.font = font;
    this.innerCell = new PdfPCell();
    innerCell.setPaddingTop(0.5f);
    setText(" ");
  }

  public PdfPCell create() {
    PdfPTable cellTable = new PdfPTable(1);
    cellTable.setWidthPercentage(100);
    cellTable.addCell(innerCell);

    PdfPCell outerCell = new PdfPCell();
    outerCell.addElement(cellTable);
    outerCell.setBorder(Rectangle.NO_BORDER);
    outerCell.setPadding(1);
    return outerCell;
  }

  public void setText(String text) {
    if (text == null) {
      setText(" ");
    }
    else {
      innerCell.setPhrase(new Phrase(text, font));
    }
  }

  public void disable() {
    innerCell.setBackgroundColor(Color.LIGHT_GRAY);
  }

  private void removeHorizontalPadding() {
    innerCell.setPaddingLeft(0);
    innerCell.setPaddingRight(0);
  }

  public void setBorder(Color borderColor, float borderWidth, int borderLocation) {
    innerCell.setBorderColor(borderColor);
    innerCell.setBorderWidth(borderWidth);
    innerCell.setBorder(borderLocation);
    if (borderLocation != Rectangle.BOX) {
      removeHorizontalPadding();
    }
  }

  public void setHorizontalAlignment(int alignment) {
    innerCell.setHorizontalAlignment(alignment);
  }
}