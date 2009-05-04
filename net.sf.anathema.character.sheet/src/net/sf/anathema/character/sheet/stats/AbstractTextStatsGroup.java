package net.sf.anathema.character.sheet.stats;

import java.awt.Color;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.sheet.table.TableEncodingUtilities;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;

public abstract class AbstractTextStatsGroup<T extends IStats> implements IStatsGroup<T> {

  public final int getColumnCount() {
    return getColumnWeights().length;
  }

  protected final PdfPCell createTextCell(Font font, String text) {
    boolean nullOrTrimmedEmpty = StringUtilities.isNullOrTrimmedEmpty(text);
    int border = nullOrTrimmedEmpty ? Rectangle.BOTTOM : Rectangle.NO_BORDER;
    String finalText = text;
    if (nullOrTrimmedEmpty) {
      finalText = " "; //$NON-NLS-1$
    }
    return TableEncodingUtilities.createContentCellTable(Color.BLACK, finalText, font, 0.5f, border, Element.ALIGN_LEFT);
  }
}