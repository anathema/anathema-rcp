package net.sf.anathema.character.sheet.combat;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.table.AbstractTableEncoder;
import net.sf.anathema.character.sheet.table.TableCell;
import net.sf.anathema.character.sheet.table.TableEncodingUtilities;
import net.sf.anathema.character.sheet.table.TableList;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class CombatRulesTableEncoder extends AbstractTableEncoder {

  private final Font commentFont;
  private final Font font;

  public CombatRulesTableEncoder(BaseFont baseFont) {
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.font = TableEncodingUtilities.createFont(baseFont);
  }

  protected final Font getCommentFont() {
    return commentFont;
  }

  protected final Font getFont() {
    return font;
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, ICharacter character, Bounds bounds)
      throws DocumentException {
    float cellPadding = 0.05f;
    PdfPTable table = new PdfPTable(new float[] { 1f, cellPadding, 1.1f, cellPadding, 1f });
    addFirstCell(table);
    table.addCell(createSpaceCell());
    addSecondCell(table);
    table.addCell(createSpaceCell());
    addThirdCell(table);
    return table;
  }

  private void addFirstCell(PdfPTable table) {
    table.addCell(new TableCell(createCombatAttackList(), Rectangle.BOX));
  }

  protected void addSecondCell(PdfPTable table) {
    Phrase phrase = new Phrase();
    addHeaderAndComment(
        phrase,
        "Knockdown",
        "Characters are knocked down if an attack deals more raw damage than their Knockdown Threshold. Roll Knockdown Pool (2) to avoid. Rising from prone requires an action. Prone characters take a -1 external penalty to all nonreflexive physical rolls.");
    phrase.add(new Chunk("\n\n", getCommentFont()));
    addHeaderAndComment(
        phrase,
        "Stunning",
        "Characters who suffer more health levels of damage than their Stun Threshold in a single blow must make a reflexive Stun roll (damage - Stamina) or be stunned until their attacker next acts. Stunned characters take a -2 internal penalty to all non-reflexive physical rolls.");
    table.addCell(createContentCell(phrase));
  }

  private void addHeaderAndComment(Phrase phrase, String header, String comment) {
    phrase.add(new Chunk(header, getFont()));
    phrase.add(new Chunk("\n", getFont()));
    phrase.add(new Chunk(comment, getCommentFont()));
  }

  private void addThirdCell(PdfPTable table) {
    table.addCell(createCommonActionsTable());
  }

  private PdfPTable createCombatAttackList() {
    TableList list = new TableList(getCommentFont());
    list.addHeader(new Chunk("Order of Attack Events", getFont()), true);
    list.addHeader(new Chunk("\n", getCommentFont()), false);
    list.addHeader(new Chunk("\n", getCommentFont()), false);
    list.addItem("Declare Attack");
    list.addItem("Declare Defence");
    list.addItem("Attack Roll");
    list.addItem("Attack Reroll");
    list.addItem("Subtract Penalties/Apply Defenses");
    list.addItem("Defence \"Reroll\"");
    list.addItem("Calculate Raw Damage");
    list.addItem("Apply Hardness & Soak, Roll Damage");
    list.addItem("Counterattack");
    list.addItem("Apply Damage, Knockdown & Stunning");
    TableCell spaceCell = new TableCell(new Phrase(" ", getCommentFont()), Rectangle.NO_BORDER);
    spaceCell.setPadding(0);
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    list.addCell(spaceCell);
    TableCell rulesCommentCell = new TableCell(
        new Phrase("Full combat rules on pages 140-158.", getCommentFont()),
        Rectangle.NO_BORDER);
    rulesCommentCell.setPadding(0);
    list.addCell(rulesCommentCell);
    return list.getTable();
  }

  private PdfPTable createCommonActionsTable() {
    float[] columnWidths = new float[] { 5, 1.5f, 1.5f };
    PdfPTable table = new PdfPTable(columnWidths);
    table.setWidthPercentage(100);
    final TableCell headerCell = createCommonActionsCell(new Phrase("Common Actions", getFont()));
    headerCell.setColspan(columnWidths.length);
    table.addCell(headerCell);
    table.addCell(createCommonActionsCell(new Phrase("Action", getCommentFont())));
    table.addCell(createCommonActionsCell(new Phrase("Speed", getCommentFont())));
    table.addCell(createCommonActionsCell(new Phrase("DV Pen", getCommentFont())));
    table.addCell(createCommonActionsCell(new Phrase(" ", getCommentFont())));
    table.addCell(createCommonActionsCell(new Phrase(" ", getCommentFont())));
    table.addCell(createCommonActionsCell(new Phrase(" ", getCommentFont())));
    addCommonActionsRow(table, "Join Battle", "Varies", "None");
    addCommonActionsRow(table, "Ready Weapon", "5", "-1");
    addCommonActionsRow(table, "Physical Attack", "Varies", "-1");
    addCommonActionsRow(table, "Coordinate Attack", "5", "None");
    addCommonActionsRow(table, "Aim", "3", "-1");
    addCommonActionsRow(table, "Guard", "3", "None");
    addCommonActionsRow(table, "Move", "0", "None");
    addCommonActionsRow(table, "Dash", "3", "-2");
    addCommonActionsRow(table, "Miscellaneous", "5", "Varies");
    addCommonActionsRow(table, "Jump", "5", "-1");
    addCommonActionsRow(table, "Rise from Prone", "5", "-1");
    addCommonActionsRow(table, "Inactive", "5", "Special");
    return table;
  }

  private void addCommonActionsRow(PdfPTable table, String actionName, String actionSpeed, String actionDV) {
    table.addCell(createCommonActionsCell(new Phrase(actionName, getCommentFont())));
    table.addCell(createCommonActionsCell(new Phrase(actionSpeed, getCommentFont())));
    table.addCell(createCommonActionsCell(new Phrase(actionDV, getCommentFont())));
  }

  private TableCell createCommonActionsCell(Phrase phrase) {
    TableCell cell = new TableCell(phrase, Rectangle.NO_BORDER);
    cell.setPadding(0);
    return cell;
  }

  private PdfPCell createSpaceCell() {
    return new TableCell(new Phrase(" ", font), Rectangle.NO_BORDER);
  }

  protected PdfPCell createContentCell(Phrase phrase) {
    return new TableCell(phrase, Rectangle.BOX);
  }
}