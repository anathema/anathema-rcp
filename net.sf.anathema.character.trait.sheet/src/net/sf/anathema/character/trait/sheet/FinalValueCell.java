package net.sf.anathema.character.trait.sheet;

import java.awt.Color;

import net.sf.anathema.character.sheet.table.ContentCellBuilder;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;

public class FinalValueCell {

  public static PdfPCell CreateEmpty(Font font) {
    return CreateForText(font, " "); //$NON-NLS-1$
  }

  public static PdfPCell CreateForValue(Font font, Integer value) {
    String content = (value != null) ? value.toString() : null;
    return CreateForText(font, content);
  }

  public static PdfPCell CreateForText(Font font, String content) {
    ContentCellBuilder cellBuilder = new ContentCellBuilder(font);
    cellBuilder.setHorizontalAlignment(Element.ALIGN_RIGHT);
    cellBuilder.setBorder(Color.BLACK, 0.75f, Rectangle.BOX);
    cellBuilder.setText(content);
    if (content == null) {
      cellBuilder.disable();
    }
    return cellBuilder.create();
  }
}