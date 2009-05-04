package net.sf.anathema.character.sheet.anima;

import static net.sf.anathema.character.sheet.anima.LineEncodingUtilities.*;
import static net.sf.anathema.character.sheet.common.IEncodeContext.*;

import java.awt.Color;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.PdfTextEncodingUtilities;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class AnimaEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  private final int fontSize;
  private final float lineHeight;
  private final BaseFont symbolBaseFont;
  private final Chunk symbolChunk;
  private final ITableEncoder tableEncoder;
  private final int animaPowerCount;

  public AnimaEncoder() {
    this.symbolBaseFont = SYMBOLFONT;
    this.fontSize = FONT_SIZE - 1;
    this.animaPowerCount = 4;
    this.lineHeight = fontSize * 1.5f;
    this.tableEncoder = new AnimaTableEncoder(fontSize);
    this.symbolChunk = CaretUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  public String getHeaderKey() {
    return "Anima"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IEncodeContext encodeContext, ICharacter character, Bounds bounds)
      throws DocumentException {
    float halfWidth = bounds.getHeight() / 2;
    Bounds animaPowerBounds = new Bounds(bounds.getMinX(), bounds.getCenterY(), bounds.getWidth(), halfWidth);
    Position lineStartPosition = encodeAnimaPowers(directContent, character, animaPowerBounds);
    if (lineStartPosition != null) {
      encodeLines(directContent, bounds, lineStartPosition);
    }
    Bounds animaTableBounds = new Bounds(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), halfWidth);
    tableEncoder.encodeTable(directContent, character, animaTableBounds);
  }

  private void encodeLines(PdfContentByte directContent, Bounds bounds, Position lineStartPosition) {
    float minX = bounds.getMinX();
    float maxX = bounds.getMaxX();
    encodeHorizontalLines(directContent, lineStartPosition, minX, maxX, lineHeight, 6 - animaPowerCount);
  }

  private Position encodeAnimaPowers(PdfContentByte directContent, ICharacter character, Bounds bounds)
      throws DocumentException {
    Phrase phrase = new Phrase("", new Font(BASEFONT, fontSize, Font.NORMAL, Color.BLACK)); //$NON-NLS-1$
    addAnimaPowerText(character, phrase);
    phrase.add(symbolChunk);
    float yPosition = PdfTextEncodingUtilities.encodeText(directContent, phrase, bounds, lineHeight).getYLine();
    return new Position((bounds.getMinX() + CaretUtilities.getCaretSymbolWidth(symbolBaseFont)), yPosition);
  }

  private void addAnimaPowerText(ICharacter character, Phrase phrase) {
    for (int power = 0; power < animaPowerCount; power++) {
      phrase.add(symbolChunk);
      phrase.add("\n"); //$NON-NLS-1$
    }
  }

  @Override
  public String getHeader(ICharacter character) {
    return "Anima";
  }
}