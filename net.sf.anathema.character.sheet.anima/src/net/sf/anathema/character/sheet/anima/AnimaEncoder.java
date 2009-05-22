package net.sf.anathema.character.sheet.anima;

import static net.sf.anathema.character.sheet.common.IEncodeContext.*;

import java.awt.Color;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.CaretSymbol;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.ISubEncoder;
import net.sf.anathema.character.sheet.content.PdfTextEncodingUtilities;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Line;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.spiritualtraits.anima.AnimaPowerExtensionPoint;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;

public class AnimaEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  private final int fontSize;
  private final float lineHeight;
  private final ISubEncoder tableEncoder;
  private final CaretSymbol caretSymbol;

  public AnimaEncoder() {
    this.fontSize = FONT_SIZE - 1;
    this.lineHeight = fontSize * 1.5f;
    this.caretSymbol = new CaretSymbol(fontSize);
    this.tableEncoder = new AnimaTableEncoder(fontSize);
  }

  public void encode(PdfContentByte directContent, IEncodeContext encodeContext, ICharacter character, Bounds bounds)
      throws DocumentException {
    float halfWidth = bounds.getHeight() / 2;
    Bounds animaPowerBounds = new Bounds(bounds.getMinX(), bounds.getCenterY(), bounds.getWidth(), halfWidth);
    String[] animaPowers = new AnimaPowerExtensionPoint().getPowers(character);
    encodeAnimaPowers(directContent, animaPowerBounds, animaPowers);
    Position lineStartPosition = encodeAnimaPowers(directContent, animaPowerBounds, animaPowers);
    Bounds animaTableBounds = new Bounds(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), halfWidth);
    if (lineStartPosition != null) {
      encodeLines(directContent, bounds, lineStartPosition, bounds.getMaxY() - 6 * lineHeight);
    }
    tableEncoder.encode(directContent, character, animaTableBounds);
  }

  private void encodeLines(PdfContentByte directContent, Bounds bounds, Position lineStartPosition, float bottomLine) {
    float minX = bounds.getMinX();
    float maxX = bounds.getMaxX();
    float lineY = lineStartPosition.y;
    while (lineY > bottomLine) {
      lineY -= lineHeight;
      Line.CreateHorizontalByCoordinate(new Position(minX, lineY), maxX).encode(directContent);
    }
  }

  private Position encodeAnimaPowers(PdfContentByte directContent, Bounds bounds, String[] powers)
      throws DocumentException {
    Phrase phrase = new Phrase("", new Font(BASEFONT, fontSize, Font.NORMAL, Color.BLACK)); //$NON-NLS-1$
    addAnimaPowerText(phrase, powers);
    float yPosition = PdfTextEncodingUtilities.encodeText(directContent, phrase, bounds, lineHeight).getYLine();
    return new Position((bounds.getMinX() + caretSymbol.getSymbolWidth()), yPosition);
  }

  private void addAnimaPowerText(Phrase phrase, String[] powers) {
    for (String power : powers) {
      phrase.add(caretSymbol.createChunk());
      phrase.add(power + "\n"); //$NON-NLS-1$
    }
  }

  @Override
  public String getHeader(ICharacter character) {
    return "Anima";
  }
}