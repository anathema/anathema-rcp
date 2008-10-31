package net.sf.anathema.character.sheet.content;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfBoxEncoder extends AbstractPdfEncoder {

  public static final int CONTENT_INSET = 5;

  private BaseFont baseFont;
  private final PdfHeaderBoxEncoder headerBoxEncoder;
  private final IPdfBoxEncoder standardBoxEncoder;

  public PdfBoxEncoder(BaseFont baseFont) {
    this.baseFont = baseFont;
    this.headerBoxEncoder = new PdfHeaderBoxEncoder(baseFont);
    this.standardBoxEncoder = new StandardBoxEncoder(baseFont);
  }

  public Bounds calculateContentBounds(Bounds bounds) {
    Bounds contentBoxBounds = calculateContentBoxBounds(bounds);
    return calculateInsettedBounds(contentBoxBounds);
  }

  private Bounds calculateContentBoxBounds(Bounds bounds) {
    int headerPadding = IPdfBoxEncoder.HEADER_HEIGHT / 2;
    return new Bounds(bounds.x, bounds.y, bounds.width, bounds.height - headerPadding);
  }

  private Bounds encodeBox(PdfContentByte directContent, Bounds bounds, String title, IPdfBoxEncoder boxEncoder) {
    Bounds contentBounds = calculateContentBoxBounds(bounds);
    boxEncoder.encodeContentBox(directContent, contentBounds);
    headerBoxEncoder.encodeHeaderBox(directContent, bounds, title);
    return calculateInsettedBounds(contentBounds);
  }

  public Bounds encodeBox(PdfContentByte directContent, Bounds bounds, String title) {
    return encodeBox(directContent, bounds, title, standardBoxEncoder);
  }

  public void encodeBox(PdfContentByte directContent, IEncodeContext context, IPdfContentBoxEncoder encoder, ICharacter character, Bounds bounds)
      throws DocumentException {
    encodeBox(directContent, context, encoder, standardBoxEncoder, character, bounds);
  }

  public void encodeBox(
      PdfContentByte directContent,
      IEncodeContext context,
      IPdfContentBoxEncoder encoder,
      IPdfBoxEncoder boxEncoder,
      ICharacter character,
      Bounds bounds) throws DocumentException {
    Bounds contentBounds = encodeBox(directContent, bounds, encoder.getHeader(character), boxEncoder);
    encoder.encode(directContent, context, character, contentBounds);
  }

  private Bounds calculateInsettedBounds(Bounds contentBounds) {
    return new Bounds(
        contentBounds.x + CONTENT_INSET,
        contentBounds.y,
        calculateInsettedWidth(contentBounds.width),
        contentBounds.height - IPdfBoxEncoder.ARCSPACE);
  }

  public float calculateInsettedWidth(float width) {
    return width - 2 * CONTENT_INSET;
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }
}