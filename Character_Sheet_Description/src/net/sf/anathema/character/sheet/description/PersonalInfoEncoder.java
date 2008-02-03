package net.sf.anathema.character.sheet.description;

import static net.sf.anathema.character.sheet.page.IVoidStateFormatConstants.*;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.core.template.CharacterTemplateProvider;
import net.sf.anathema.character.description.ICharacterDescription;
import net.sf.anathema.character.report.text.CharacterTextContainer;
import net.sf.anathema.character.report.text.ICharacterText;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.AbstractPdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PersonalInfoEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private BaseFont baseFont;

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
    baseFont = context.getBaseFont();
    ICharacterDescription description = (ICharacterDescription) character.getModel(ICharacterDescription.MODEL_ID);
    float lineHeight = (bounds.height - TEXT_PADDING) / 3;
    float entryWidth = (bounds.width - TEXT_PADDING) / 2;
    float firstColumnX = bounds.x;
    float secondColumnX = bounds.x + entryWidth + TEXT_PADDING;
    float firstRowY = (int) (bounds.getMaxY() - lineHeight);
    drawLabelledContent(
        directContent,
        Messages.PersonalInfoEncoder_TemplateLabel,
        new CharacterTemplateProvider().getTemplate(character.getTemplateId()).getName(),
        new Position(firstColumnX, firstRowY),
        entryWidth);
    String playerName = description.getPlayer().getText();
    drawLabelledContent(directContent, Messages.PersonalInfoEncoder_PlayerLabel, playerName, new Position(
        secondColumnX,
        firstRowY), entryWidth);
    float secondRowY = firstRowY - lineHeight;
    String conceptContent = description.getConcept().getText();
    drawLabelledContent(directContent, Messages.PersonalInfoEncoder_ConceptLabel, conceptContent, new Position(
        firstColumnX,
        secondRowY), entryWidth);
    ICharacterText casteText = new CharacterTextContainer().getText("caste"); //$NON-NLS-1$
    if (casteText != null && casteText.isActiveFor(character)) {
      String casteContent = casteText.getTextFor(character);
      String label = casteText.getLabel();
      drawLabelledContent(directContent, label, casteContent, new Position(secondColumnX, secondRowY), entryWidth);
    }
    float thirdRowY = secondRowY - lineHeight;
    String motivationContent = null;
    Position motivationPosition = new Position(firstColumnX, thirdRowY);
    drawLabelledContent(
        directContent,
        Messages.PersonalInfoEncoder_MotivationLabel,
        motivationContent,
        motivationPosition,
        bounds.width);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  @Override
  public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
      throws CoreException {
    // nothing to do
  }
}