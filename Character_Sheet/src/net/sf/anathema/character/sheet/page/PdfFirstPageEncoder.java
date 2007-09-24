package net.sf.anathema.character.sheet.page;

import static net.sf.anathema.character.sheet.page.IVoidStateFormatConstants.*;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.ICharacter;
import net.sf.anathema.character.sheet.content.IContentEncoderProvider;
import net.sf.anathema.character.sheet.content.PdfBoxEncoder;
import net.sf.anathema.character.sheet.content.PdfTextEncodingUtilities;
import net.sf.anathema.character.sheet.elements.Bounds;

import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfFirstPageEncoder implements IPdfPageEncoder {
  private static final String ENCODER_INTIMACIES = "encoder.intimacies"; //$NON-NLS-1$
  private static final String ENCODER_GREATCURSE = "encoder.greatcurse"; //$NON-NLS-1$
  private static final String ENCODER_WILLPOWER = "encoder.willpower"; //$NON-NLS-1$
  private static final String ENCODER_WEAPONRY = "encoder.weaponry"; //$NON-NLS-1$
  private static final String ENCODER_VIRTUES = "encoder.virtues"; //$NON-NLS-1$
  private static final String ENCODER_HEALTH = "encoder.health"; //$NON-NLS-1$
  private static final String ENCODER_COMBATSTATS = "encoder.combatstats"; //$NON-NLS-1$
  private static final String ENCODER_SOCIALCOMBAT = "encoder.socialcombat"; //$NON-NLS-1$
  private static final String ENCODER_ARMOUR = "encoder.armour"; //$NON-NLS-1$
  private static final String ENCODER_ANIMA = "encoder.anima"; //$NON-NLS-1$
  private static final String ENCODER_ATTRIBUTES = "encoder.attributes"; //$NON-NLS-1$
  private static final String ENCODER_ABILITIES = "encoder.abilities"; //$NON-NLS-1$
  private static final String ENCODER_ESSENCE = "encoder.essence"; //$NON-NLS-1$
  private static final String ENCODER_PERSONAL_INFO = "encoder.personalInfo"; //$NON-NLS-1$
  public static final int CONTENT_HEIGHT = 755;
  private static final int ANIMA_HEIGHT = 128;
  private final BaseFont baseFont;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final IContentEncoderProvider contentEncoderProvider;

  public PdfFirstPageEncoder(
      IContentEncoderProvider boxEncoderProvider,
      PdfPageConfiguration pageConfiguration,
      BaseFont baseFont) {
    this.contentEncoderProvider = boxEncoderProvider;
    this.baseFont = baseFont;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(baseFont);
  }

  private float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  public void encode(
      Document document,
      PdfContentByte directContent,
      ICharacter character) throws DocumentException {
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(directContent, character, distanceFromTop, firstRowHeight);
    encodeEssence(directContent, character, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    encodeFirstColumn(directContent, character, distanceFromTop);
    encodeAnima(directContent, character, distanceFromTop, ANIMA_HEIGHT);
    float virtueHeight = encodeVirtues(directContent, distanceFromTop, 72, character);
    distanceFromTop += calculateBoxIncrement(virtueHeight);
    float greatCurseHeigth = ANIMA_HEIGHT - virtueHeight - IVoidStateFormatConstants.PADDING;
    encodeGreatCurse(directContent, character, distanceFromTop, greatCurseHeigth);
    distanceFromTop += calculateBoxIncrement(greatCurseHeigth);

    float socialCombatHeight = encodeSocialCombatStats(directContent, character, distanceFromTop, 115);
    float willpowerHeight = encodeWillpower(directContent, character, distanceFromTop, 43);
    float willpowerIncrement = calculateBoxIncrement(willpowerHeight);
    distanceFromTop += willpowerIncrement;
    float intimaciesHeight = encodeIntimacies(directContent, character, distanceFromTop, socialCombatHeight
        - willpowerIncrement);
    distanceFromTop += calculateBoxIncrement(intimaciesHeight);
    float weaponryHeight = encodeWeaponry(directContent, character, distanceFromTop, 102);
    distanceFromTop += calculateBoxIncrement(weaponryHeight);
    float armourHeight = encodeArmourAndSoak(directContent, character, distanceFromTop, 80);
    distanceFromTop += calculateBoxIncrement(armourHeight);
    float healthHeight = encodeMovementAndHealth(directContent, character, distanceFromTop, 99);
    distanceFromTop += calculateBoxIncrement(healthHeight);
    float remainingHeight = PdfFirstPageEncoder.CONTENT_HEIGHT - distanceFromTop;
    encodeCombatStats(directContent, character, distanceFromTop, remainingHeight);
    encodeCopyright(directContent);
  }

  private void encodeAbilities(PdfContentByte directContent, ICharacter character, int distanceFromTop)
      throws DocumentException {
    int abilitiesHeight = CONTENT_HEIGHT - distanceFromTop;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_ABILITIES, character);
    boxEncoder.encodeBox(directContent, encoder, character, boxBounds);
  }

  private void encodeAnima(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds animaBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_ANIMA, character);
    boxEncoder.encodeBox(directContent, encoder, character, animaBounds);
  }
  
  private float encodeArmourAndSoak(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_ARMOUR, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private int encodeAttributes(PdfContentByte directContent, ICharacter character, int distanceFromTop)
      throws DocumentException {
    int attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_ATTRIBUTES, character);
    boxEncoder.encodeBox(directContent, encoder, character, attributeBounds);
    return attributeHeight;
  }

  private float encodeCombatStats(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_COMBATSTATS, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private void encodeCopyright(PdfContentByte directContent) throws DocumentException {
    int lineHeight = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 2;
    Font copyrightFont = new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    float copyrightHeight = pageConfiguration.getPageHeight() - pageConfiguration.getContentHeight();
    Bounds firstColumnBounds = pageConfiguration.getFirstColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    Anchor voidstatePhrase = new Anchor("Inspired by Voidstate\nhttp://www.voidstate.com", copyrightFont); //$NON-NLS-1$
    voidstatePhrase.setReference("http://www.voidstate.com"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(directContent, voidstatePhrase, firstColumnBounds, lineHeight);
    Anchor anathemaPhrase = new Anchor("Created with Anathema \u00A92007\nhttp://anathema.sf.net", copyrightFont); //$NON-NLS-1$
    anathemaPhrase.setReference("http://anathema.sf.net"); //$NON-NLS-1$
    Bounds anathemaBounds = pageConfiguration.getSecondColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    PdfTextEncodingUtilities.encodeText(directContent, anathemaPhrase, anathemaBounds, lineHeight, Element.ALIGN_CENTER);
    Anchor whitewolfPhrase = new Anchor("Exalted \u00A92007 by White Wolf, Inc.\nhttp://www.white-wolf.com", //$NON-NLS-1$
        copyrightFont);
    whitewolfPhrase.setReference("http://www.white-wolf.com"); //$NON-NLS-1$
    Bounds whitewolfBounds = pageConfiguration.getThirdColumnRectangle(CONTENT_HEIGHT, copyrightHeight);
    PdfTextEncodingUtilities.encodeText(
        directContent,
        whitewolfPhrase,
        whitewolfBounds,
        lineHeight,
        Element.ALIGN_RIGHT);
  }

  private float encodeEssence(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_ESSENCE, character);
    boxEncoder.encodeBox(directContent, encoder, character, essenceBounds);
    return height;
  }

  private void encodeFirstColumn(PdfContentByte directContent, ICharacter character, int distanceFromTop)
      throws DocumentException {
    int attributeHeight = encodeAttributes(directContent, character, distanceFromTop);
    encodeAbilities(directContent, character, distanceFromTop + attributeHeight + PADDING);
  }

  private float encodeGreatCurse(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_GREATCURSE, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeIntimacies(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_INTIMACIES, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeMovementAndHealth(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_HEALTH, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }


  private void encodePersonalInfo(
      PdfContentByte directContent,
      ICharacter character,
      int distanceFromTop,
      final int firstRowHeight) throws DocumentException {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    IPdfContentBoxEncoder contentEncoder = contentEncoderProvider.getContentEncoder(ENCODER_PERSONAL_INFO, character);
    boxEncoder.encodeBox(directContent, contentEncoder, character, infoBounds);
  }

  private float encodeSocialCombatStats(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, height);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_SOCIALCOMBAT, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeVirtues(
      PdfContentByte directContent,
      float distanceFromTop,
      float height,
      ICharacter character) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_VIRTUES, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeWeaponry(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 2);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_WEAPONRY, character);
    boxEncoder.encodeBox(directContent, encoder, character, bounds);
    return height;
  }

  private float encodeWillpower(
      PdfContentByte directContent,
      ICharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds willpowerBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, height, 1);
    IPdfContentBoxEncoder encoder = contentEncoderProvider.getContentEncoder(ENCODER_WILLPOWER, character);
    boxEncoder.encodeBox(directContent, encoder, character, willpowerBounds);
    return height;
  }
}