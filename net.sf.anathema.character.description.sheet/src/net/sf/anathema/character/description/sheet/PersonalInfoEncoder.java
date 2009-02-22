package net.sf.anathema.character.description.sheet;

import static net.sf.anathema.character.sheet.page.IVoidStateFormatConstants.*;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.description.ICharacterDescription;
import net.sf.anathema.character.report.text.CharacterTextContainer;
import net.sf.anathema.character.report.text.ICharacterText;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class PersonalInfoEncoder extends AbstractExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public String getHeader(ICharacter character) {
    ICharacterDescription description = (ICharacterDescription) character.getModel(ICharacterDescription.MODEL_ID);
    String characterName = description.getName().getText();
    if (StringUtilities.isNullOrTrimmedEmpty(characterName)) {
      return Messages.PersonalInfoEncoder_EncoderTitle;
    }
    return characterName;
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    PdfEncoder pdfEncoder = new PdfEncoder(directContent);
    ICharacterDescription description = (ICharacterDescription) character.getModel(ICharacterDescription.MODEL_ID);
    float lineHeight = (bounds.height - TEXT_PADDING) / 3;
    float entryWidth = (bounds.width - TEXT_PADDING) / 2;
    float firstColumnX = bounds.x;
    float secondColumnX = bounds.x + entryWidth + TEXT_PADDING;
    float firstRowY = (int) (bounds.getMaxY() - lineHeight);
    pdfEncoder.drawLabelledContent(Messages.PersonalInfoEncoder_TemplateLabel, character.getTemplate()
        .getName(), new Position(firstColumnX, firstRowY), entryWidth);
    String playerName = description.getPlayer().getText();
    pdfEncoder.drawLabelledContent(Messages.PersonalInfoEncoder_PlayerLabel, playerName, new Position(
        secondColumnX,
        firstRowY), entryWidth);
    float secondRowY = firstRowY - lineHeight;
    String conceptContent = description.getConcept().getText();
    pdfEncoder.drawLabelledContent(
        Messages.PersonalInfoEncoder_ConceptLabel,
        conceptContent,
        new Position(firstColumnX, secondRowY),
        entryWidth);
    ICharacterText casteText = new CharacterTextContainer().getText("caste"); //$NON-NLS-1$
    if (casteText != null && casteText.isActiveFor(character)) {
      String casteContent = casteText.getTextFor(character);
      String label = casteText.getLabel();
      pdfEncoder.drawLabelledContent(
          label,
          casteContent,
          new Position(secondColumnX, secondRowY),
          entryWidth);
    }
    float thirdRowY = secondRowY - lineHeight;
    String motivationContent = null;
    Position motivationPosition = new Position(firstColumnX, thirdRowY);
    pdfEncoder.drawLabelledContent(
        Messages.PersonalInfoEncoder_MotivationLabel,
        motivationContent,
        motivationPosition,
        bounds.width);
  }
}