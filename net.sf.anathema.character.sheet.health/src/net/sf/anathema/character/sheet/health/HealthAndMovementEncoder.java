package net.sf.anathema.character.sheet.health;

import java.awt.Color;

import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.common.CaretSymbol;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.content.PdfTextEncodingUtilities;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;
import net.sf.anathema.character.sheet.table.ITableEncoder;
import net.sf.anathema.character.sheet.table.TableEncodingUtilities;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;

public class HealthAndMovementEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  @Override
  public String getHeader(ICharacter character) {
    return "Movement & Health";
  }

  @Override
  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    Bounds tableBounds = new Bounds(bounds.x, bounds.y, (bounds.width * 0.66f), bounds.height);
    ITableEncoder tableEncoder = new HealthAndMovementTableEncoder(context.getBaseFont());
    tableEncoder.encodeTable(directContent, character, tableBounds);
    float textX = tableBounds.getMaxX() + IVoidStateFormatConstants.TEXT_PADDING;
    Bounds textBounds = new Bounds(textX, bounds.y, bounds.x + bounds.width - textX, bounds.height - 2);
    encodeText(directContent, textBounds, context.getBaseFont());
  }

  private void encodeText(PdfContentByte directContent, Bounds textBounds, BaseFont baseFont) throws DocumentException {
    IGraphicalEncoder graphicalEncoder = new PdfEncoder(directContent);
    Font headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    Font commentFont = new Font(baseFont, IVoidStateFormatConstants.COMMENT_FONT_SIZE, Font.NORMAL, Color.BLACK);
    Font commentTitleFont = new Font(commentFont);
    commentTitleFont.setStyle(Font.BOLD);
    Paragraph healthText = createHealthRulesPhrase(headerFont, commentFont, commentTitleFont);
    int leading = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 1;
    ColumnText text = PdfTextEncodingUtilities.encodeText(directContent, healthText, textBounds, leading);
    int rectangleOffset = HealthAndMovementTableEncoder.HEALTH_RECT_SIZE + 1;
    final float additionalOffset = 2.5f;
    float rectYPosition = text.getYLine() - rectangleOffset - additionalOffset;
    float textYPosition = text.getYLine() - leading - additionalOffset;
    float xPosition = textBounds.x;
    PdfTemplate rectTemplate = HealthAndMovementTableEncoder.createRectTemplate(directContent, Color.BLACK);
    directContent.addTemplate(rectTemplate, xPosition, rectYPosition);
    PdfTemplate bashingTemplate = HealthAndMovementTableEncoder.createBashingTemplate(directContent, Color.GRAY);
    directContent.addTemplate(bashingTemplate, xPosition, rectYPosition);
    xPosition += rectangleOffset;
    final String createSpacedString = createSpacedString("Bashing");
    String bashingString = createSpacedString;
    graphicalEncoder.drawComment(bashingString, new Position(xPosition, textYPosition), Element.ALIGN_LEFT);
    xPosition += graphicalEncoder.getCommentTextWidth(bashingString);
    directContent.addTemplate(rectTemplate, xPosition, rectYPosition);
    PdfTemplate lethalTemplate = HealthAndMovementTableEncoder.createLethalTemplate(directContent, Color.GRAY);
    directContent.addTemplate(lethalTemplate, xPosition, rectYPosition);
    xPosition += rectangleOffset;
    String lethalString = createSpacedString("Lethal");
    graphicalEncoder.drawComment(lethalString, new Position(xPosition, textYPosition), Element.ALIGN_LEFT);
    xPosition += graphicalEncoder.getCommentTextWidth(lethalString);
    directContent.addTemplate(rectTemplate, xPosition, rectYPosition);
    PdfTemplate aggravatedTemplate = HealthAndMovementTableEncoder.createAggravatedTemplate(directContent, Color.GRAY);
    directContent.addTemplate(aggravatedTemplate, xPosition, rectYPosition);
    xPosition += rectangleOffset;
    String aggravatedString = createSpacedString("Aggravated");
    graphicalEncoder.drawComment(aggravatedString, new Position(xPosition, textYPosition), Element.ALIGN_LEFT);
    xPosition += graphicalEncoder.getCommentTextWidth(lethalString);
  }

  private String createSpacedString(final String string) {
    return " " + string + "   "; //$NON-NLS-1$ //$NON-NLS-2$
  }

  private Paragraph createHealthRulesPhrase(Font headerFont, Font commentFont, Font commentTitleFont) {
    Paragraph healthText = new Paragraph();
    healthText.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
    final Chunk seperator = new Chunk(": ", commentTitleFont); //$NON-NLS-1$
    final Chunk newLine = new Chunk("\n", commentFont); //$NON-NLS-1$
    final Chunk header = new Chunk("Rules", headerFont);
    final Chunk caretChunk = new CaretSymbol(IEncodeContext.FONT_SIZE - 1).createChunk();
    healthText.add(header);
    healthText.add(newLine);
    healthText.add(caretChunk);
    healthText.add(new Chunk("Health", commentTitleFont));
    healthText.add(seperator);
    healthText.add(new Chunk(
        "Bashing damage heals 1 health level per 3 hours. Lethal damage healing rate varies (-0 = 6 hours; -1 = 2 days; -2 = 4 days; -4 = 1 week; Incapacitated = 1 week). Double these times if not resting. Aggravated damage heals at the same rate as lethal but cannot be healed magically.",
        commentFont));
    healthText.add(newLine);
    healthText.add(caretChunk);
    healthText.add(new Chunk("Death and Dying", commentTitleFont));
    healthText.add(seperator);
    healthText.add(new Chunk(
        "If characters incapacitated by Lethal or Aggravated suffer further damage, they lose one Dying level per \"Inactive\" action.",
        commentFont));
    healthText.add(newLine);
    healthText.add(caretChunk);
    healthText.add(new Chunk("Marking Damage", commentTitleFont));
    healthText.add(seperator);
    return healthText;
  }
}