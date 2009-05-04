package net.sf.anathema.character.sheet.table;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;

public abstract class AbstractTableEncoder implements ITableEncoder {

  @Override
  public final float encodeTable(PdfContentByte directContent, ICharacter character, Bounds bounds)
      throws DocumentException {
    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable(directContent, character, bounds);
    table.setWidthPercentage(100);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
    return table.getTotalHeight();
  }

  protected abstract PdfPTable createTable(PdfContentByte directContent, ICharacter character, Bounds bounds)
      throws DocumentException;
}