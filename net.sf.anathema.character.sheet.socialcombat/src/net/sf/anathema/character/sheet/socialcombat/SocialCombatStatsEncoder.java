package net.sf.anathema.character.sheet.socialcombat;

import static net.sf.anathema.character.sheet.common.IEncodeContext.*;
import net.sf.anathema.basics.eclipse.extension.UnconfiguredExecutableExtension;
import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.derivedtraits.SocialCombatCharacter;
import net.sf.anathema.character.sheet.common.IEncodeContext;
import net.sf.anathema.character.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.sheet.content.IGraphicalEncoder;
import net.sf.anathema.character.sheet.content.ISubEncoder;
import net.sf.anathema.character.sheet.content.PdfEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.elements.Position;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;
import net.sf.anathema.character.sheet.table.TableCell;
import net.sf.anathema.character.sheet.table.TableEncodingUtilities;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;

public class SocialCombatStatsEncoder extends UnconfiguredExecutableExtension implements IPdfContentBoxEncoder {

  private final Font commentFont;
  private final Font font;

  public SocialCombatStatsEncoder() {
    this.commentFont = TableEncodingUtilities.createCommentFont(BASEFONT);
    this.font = TableEncodingUtilities.createFont(BASEFONT);
  }

  public void encode(PdfContentByte directContent, IEncodeContext context, ICharacter character, Bounds bounds)
      throws DocumentException {
    float valueWidth = bounds.width;
    IGraphicalEncoder graphicalEncoder = new PdfEncoder(directContent);
    Bounds valueBounds = new Bounds(bounds.x, bounds.y, valueWidth, bounds.height);
    SocialCombatCharacter socialCombatCharacter = new SocialCombatCharacter(character);
    float valueHeight = encodeValues(graphicalEncoder, valueBounds, socialCombatCharacter);
    Bounds attackTableBounds = new Bounds(bounds.x, bounds.y, valueWidth, bounds.height - valueHeight);
    ISubEncoder tableEncoder = new SocialCombatStatsTableEncoder(BASEFONT);
    float attackHeight = tableEncoder.encode(directContent, character, attackTableBounds);
    Bounds actionBounds = new Bounds(bounds.x, bounds.y, valueWidth / 2f, attackTableBounds.height - attackHeight);
    encodeActionTable(directContent, actionBounds);
    final float center = bounds.x + valueWidth / 2f;
    Bounds commentBounds = new Bounds(center + 4, bounds.y, valueWidth / 2f, attackTableBounds.height - attackHeight);
    encodeDVTable(directContent, commentBounds);
    directContent.moveTo(center, bounds.y + 6 * IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    directContent.lineTo(center, bounds.y + 3);
  }

  private void encodeDVTable(PdfContentByte directContent, Bounds bounds) throws DocumentException {
    float[] columnWidths = new float[] { 4, 5 };
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    final TableCell headerCell = createCommonActionsCell(new Phrase("Common DV Modifiers", font));
    headerCell.setColspan(columnWidths.length);
    headerCell.setPaddingTop(1.5f);
    table.addCell(headerCell);
    table.addCell(createCommonActionsCell(new Phrase("Source", commentFont)));
    table.addCell(createCommonActionsCell(new Phrase("Modifier", commentFont)));
    table.addCell(createCommonActionsCell(createEmptyPhrase()));
    table.addCell(createCommonActionsCell(createEmptyPhrase()));
    createCommonDVRow(table, "Appearance", "Difference");
    createCommonDVRow(table, "Motivation", "+/- 3");
    createCommonDVRow(table, "Virtue", "+/- 2");
    createCommonDVRow(table, "Intimacy", "+/- 1");
    ColumnText tableColumn = new ColumnText(directContent);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
  }

  private void createCommonDVRow(PdfPTable table, String sourceName, String dvModifier) {
    table.addCell(createCommonActionsCell(new Phrase(sourceName, commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(dvModifier, commentFont)));
  }

  private float encodeActionTable(PdfContentByte directContent, Bounds bounds) throws DocumentException {
    float[] columnWidths = new float[] { 4, 2.5f, 2f };
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    final TableCell headerCell = createCommonActionsCell(new Phrase("Common Actions", font));
    headerCell.setColspan(columnWidths.length);
    headerCell.setPaddingTop(1.5f);
    table.addCell(headerCell);
    table.addCell(createCommonActionsCell(new Phrase("Action", commentFont)));
    table.addCell(createCommonActionsCell(new Phrase("Speed", commentFont)));
    table.addCell(createCommonActionsCell(new Phrase("DV", commentFont)));
    table.addCell(createCommonActionsCell(createEmptyPhrase()));
    table.addCell(createCommonActionsCell(createEmptyPhrase()));
    table.addCell(createCommonActionsCell(createEmptyPhrase()));
    addCommonActionsRow(table, "Join Debate", "5", "None");
    addCommonActionsRow(table, "Attack", "Above", "-2");
    addCommonActionsRow(table, "Monologue", "3", "-2");
    addCommonActionsRow(table, "Misc", "5", "-2");
    ColumnText tableColumn = new ColumnText(directContent);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
    return table.getTotalHeight();
  }

  private Phrase createEmptyPhrase() {
    return new Phrase(" ", commentFont); //$NON-NLS-1$
  }

  private void addCommonActionsRow(PdfPTable table, String actionName, String actionSpeed, String actionDV) {
    table.addCell(createCommonActionsCell(new Phrase(actionName, commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(actionSpeed, commentFont)));
    table.addCell(createCommonActionsCell(new Phrase(actionDV, commentFont)));
  }

  private TableCell createCommonActionsCell(Phrase phrase) {
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    return cell;
  }

  private float encodeValues(IGraphicalEncoder graphicalEncoder, Bounds bounds, SocialCombatCharacter character) {
    int joinDebate = character.getJoinDebate();
    int dodgeMDV = character.getDodgeMdv();
    Position upperLeftCorner = new Position(bounds.x, bounds.getMaxY());
    LabelledValueEncoder encoder = new LabelledValueEncoder(2, upperLeftCorner, bounds.width, 3);
    encoder.addLabelledValue(graphicalEncoder, 0, "Join Debate", joinDebate);
    encoder.addLabelledValue(graphicalEncoder, 1, "Dodge MDV", dodgeMDV);
    return encoder.getHeight() + 1;
  }

  @Override
  public String getHeader(ICharacter character) {
    return "Social Combat";
  }
}