package net.sf.anathema.character.sheet.common;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.sheet.content.HorizontalLineListEncoder;
import net.sf.anathema.character.sheet.content.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfHorizontalLineContentEncoder extends AbstractExecutableExtension implements IPdfContentBoxEncoder {

  private static final int LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final int columnCount;
  private final String header;

  public String getHeader() {
    return header;
  }

  public PdfHorizontalLineContentEncoder(int columnCount, String header) {
    this.columnCount = columnCount;
    this.header = header;
  }

  public void encode(PdfContentByte directContent, ICharacter character, Bounds bounds) throws DocumentException {
    float columnWidth = (bounds.width - (columnCount - 1) * IVoidStateFormatConstants.TEXT_PADDING) / columnCount;
    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
      float columnX = bounds.x + columnIndex * columnWidth + columnIndex * IVoidStateFormatConstants.TEXT_PADDING;
      Bounds columnBounds = new Bounds(columnX, bounds.y, columnWidth, bounds.height);
      new HorizontalLineListEncoder().encodeLines(directContent, columnBounds, LINE_HEIGHT);
    }
  }
}