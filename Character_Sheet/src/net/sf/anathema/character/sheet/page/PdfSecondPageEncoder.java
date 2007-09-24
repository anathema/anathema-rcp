package net.sf.anathema.character.sheet.page;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.IDynamicPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;
import net.sf.anathema.character.sheet.content.PdfBoxEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfSecondPageEncoder implements IPdfPageEncoder {

  private static final String ENCODER_GENERICCHARMS = "encoder.genericcharms"; //$NON-NLS-1$
  private static final String ENCODER_MAGIC = "encoder.magic"; //$NON-NLS-1$
  private static final String ENCODER_POSSESSIONS = "encoder.possessions"; //$NON-NLS-1$
  private static final String ENCODER_BACKGROUNDS = "encoder.backgrounds"; //$NON-NLS-1$
  private static final String ENCODER_EXPERIENCE = "encoder.experience"; //$NON-NLS-1$
  private static final String ENCODER_LANGUAGES = "encoder.languages"; //$NON-NLS-1$
  private final PdfPageConfiguration configuration;
  private final PdfBoxEncoder boxEncoder;
  private final IContentEncoderProvider contentEncoderProvider;

  public PdfSecondPageEncoder(
      IContentEncoderProvider contentEncoderProvider,
      PdfPageConfiguration configuration,
      BaseFont baseFont) {
    this.contentEncoderProvider = contentEncoderProvider;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder(baseFont);
  }

  public void encode(Document document, PdfContentByte directContent, ICharacter character) throws DocumentException {
    float distanceFromTop = 0;
    float languageHeight = 60;
    float backgroundHeight = 104;
    float experienceHeight = backgroundHeight - languageHeight - IVoidStateFormatConstants.PADDING;
    encodeBackgrounds(directContent, character, distanceFromTop, backgroundHeight);
    encodePossessions(directContent, character, distanceFromTop, backgroundHeight);
    encodeLanguages(directContent, character, distanceFromTop, languageHeight);
    distanceFromTop += languageHeight + IVoidStateFormatConstants.PADDING;
    encodeExperience(directContent, character, distanceFromTop, experienceHeight);
    distanceFromTop += experienceHeight + IVoidStateFormatConstants.PADDING;
    float comboHeight = encodeCombos(directContent, character, distanceFromTop);
    if (comboHeight > 0) {
      distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
    }
    // TODO schauen, ob die Charms noch passen
    float genericCharmsHeight = encodeGenericCharms(directContent, character, distanceFromTop);
    distanceFromTop += genericCharmsHeight + IVoidStateFormatConstants.PADDING;

    float remainingHeight = configuration.getContentHeight() - distanceFromTop;
    encodeCharms(directContent, character, distanceFromTop, remainingHeight);
  }

  private float encodeCombos(PdfContentByte directContent, ICharacter character, float distanceFromTop)
      throws DocumentException {
    IDynamicPdfContentBoxEncoder encoder = contentEncoderProvider.getDynamicContentEncoder(ENCODER_LANGUAGES, character);
    float height = encoder.getHeight();
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return encoder.getHeight();
  }

  private float encodeExperience(PdfContentByte directContent, ICharacter character, float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_EXPERIENCE, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeLanguages(PdfContentByte directContent, ICharacter character, float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_LANGUAGES, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeBackgrounds(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds backgroundBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_BACKGROUNDS, character);
    boxEncoder.encodeBox(directContent, encoder, character, backgroundBounds);
    return height;
  }

  private float encodePossessions(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = configuration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_POSSESSIONS, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeGenericCharms(PdfContentByte directContent, ICharacter character, float distanceFromTop)
      throws DocumentException {
    IDynamicPdfContentBoxEncoder encoder = contentEncoderProvider.getDynamicContentEncoder(
        ENCODER_GENERICCHARMS,
        character);
    float height = encoder.getHeight();
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeCharms(PdfContentByte directContent, ICharacter character, float distanceFromTop, float height)
      throws DocumentException {
    Bounds bounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 3);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_MAGIC, character);
    boxEncoder.encodeBox(directContent, encoder, null, bounds);
    return height;
  }
}