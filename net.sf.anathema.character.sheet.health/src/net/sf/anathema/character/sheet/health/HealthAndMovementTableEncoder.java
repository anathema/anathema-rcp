package net.sf.anathema.character.sheet.health;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.content.ISubEncoder;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.table.TableCell;
import net.sf.anathema.character.sheet.table.TableEncodingUtilities;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;

public class HealthAndMovementTableEncoder implements ISubEncoder {
  public static final int HEALTH_RECT_SIZE = 6;
  private static final int HEALTH_COLUMN_COUNT = 10;
  protected static float PADDING = 0.3f;
  private static final float[] HEALTH_LEVEL_COLUMNS = new float[] { PADDING, 0.6f, 0.7f, PADDING };
  private static final float[] MOVEMENT_COLUMNS = new float[] { 1f, PADDING, 1f, PADDING, 1f, 1f };
  private static final float[] HEALTH_COLUMNS = new float[] {
      0.4f,
      0.4f,
      0.4f,
      0.4f,
      0.4f,
      0.4f,
      0.4f,
      0.4f,
      0.4f,
      0.4f };

  private final Font font;
  private final Font headerFont;
  private final Font commentFont;
  private final PdfPCell spaceCell;
  private static Map<HealthLevelType, String> penaltyTextByType = new HashMap<HealthLevelType, String>() {
    {
      put(HealthLevelType.Zero, "-0");
      put(HealthLevelType.One, "-1");
      put(HealthLevelType.Two, "-2");
      put(HealthLevelType.Four, "-4");
      put(HealthLevelType.Incapacitated, "Incap.");
    }
  };

  public HealthAndMovementTableEncoder(BaseFont baseFont) {
    this.font = TableEncodingUtilities.createFont(baseFont);
    this.headerFont = TableEncodingUtilities.createHeaderFont(baseFont);
    this.commentFont = TableEncodingUtilities.createCommentFont(baseFont);
    this.spaceCell = new PdfPCell(new Phrase(" ", font)); //$NON-NLS-1$
    this.spaceCell.setBorder(Rectangle.NO_BORDER);
  }

  @Override
  public float encode(PdfContentByte directContent, ICharacter character, Bounds bounds) throws DocumentException {
    ColumnText tableColumn = new ColumnText(directContent);
    PdfPTable table = createTable(directContent, character);
    table.setWidthPercentage(100);
    tableColumn.setSimpleColumn(bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
    tableColumn.addElement(table);
    tableColumn.go();
    return table.getTotalHeight();
  }

  private int getRowCount(HealthLevelType type) {
    if (type == HealthLevelType.Two || type == HealthLevelType.One) {
      return 2;
    }
    return 1;
  }

  protected final PdfPTable createTable(PdfContentByte directContent, ICharacter character) throws DocumentException {
    try {
      Image activeTemplate = Image.getInstance(createRectTemplate(directContent, Color.BLACK));
      Image passiveTemplate = Image.getInstance(createRectTemplate(directContent, Color.LIGHT_GRAY));
      float[] columnWidth = createColumnWidth();
      PdfPTable table = new PdfPTable(columnWidth);
      addHeaders(table);
      for (HealthLevelType type : HealthLevelType.values()) {
        addHealthTypeRows(table, character, activeTemplate, passiveTemplate, type);
      }
      return table;
    }
    catch (BadElementException e) {
      throw new DocumentException(e);
    }
  }

  private void addHealthTypeRows(
      PdfPTable table,
      ICharacter character,
      Image activeTemplate,
      Image passiveTemplate,
      HealthLevelType type) {
    IHealthyCharacter healthyCharacter = new HealthyCharacter(character);
    int rowCount = getRowCount(type);
    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      if (rowIndex == 0) {
        if (type == HealthLevelType.Incapacitated) {
          addIncapacitatedMovement(table);
        }
        else {
          addMovementCells(table, type, healthyCharacter);
        }
        addHealthTypeCells(table, type, healthyCharacter);
      }
      else {
        addSpaceCells(table, MOVEMENT_COLUMNS.length + HEALTH_LEVEL_COLUMNS.length);
      }
      addHealthCells(table, healthyCharacter, type, rowIndex, activeTemplate, passiveTemplate);
    }
  }

  private void addHealthTypeCells(PdfPTable table, HealthLevelType type, IHealthyCharacter character) {
    addSpaceCells(table, 1);
    addHealthPenaltyCells(table, type, character);
    addSpaceCells(table, 1);
  }

  private void addIncapacitatedMovement(PdfPTable table) {
    final Phrase commentPhrase = new Phrase("Subtract Mob. Pen. Twice for h. jump.", getCommentFont());
    final TableCell cell = new TableCell(commentPhrase, Rectangle.NO_BORDER);
    cell.setColspan(MOVEMENT_COLUMNS.length);
    cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    table.addCell(cell);
  }

  protected final void addMovementHeader(PdfPTable table) {
    table.addCell(createHeaderCell("Move", 2));
    table.addCell(createHeaderCell("Dash", 2));
    table.addCell(createHeaderCell("Jump", 3));
  }

  protected final void addSpaceCells(PdfPTable table, int count) {
    for (int index = 0; index < count; index++) {
      table.addCell(spaceCell);
    }
  }

  protected final PdfPCell createHeaderCell(String text, int columnSpan) {
    PdfPCell cell = new PdfPCell(new Phrase(text, headerFont));
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setColspan(columnSpan);
    return cell;
  }

  private void addHeaders(PdfPTable table) {
    addMovementHeader(table);
    table.addCell(createHeaderCell("Health Levels", 13));
  }

  public static PdfTemplate createRectTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate activeHealthRect = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    activeHealthRect.setLineWidth(1f);
    activeHealthRect.setColorStroke(strokeColor);
    activeHealthRect.rectangle(0, 0, HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    activeHealthRect.stroke();
    return activeHealthRect;
  }

  public static PdfTemplate createBashingTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate bashingSlash = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    bashingSlash.setLineWidth(1f);
    bashingSlash.setColorStroke(strokeColor);
    bashingSlash.moveTo(0, 0);
    bashingSlash.lineTo(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    bashingSlash.stroke();
    return bashingSlash;
  }

  public static PdfTemplate createLethalTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate lethalCross = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    lethalCross.addTemplate(createBashingTemplate(directContent, strokeColor), 0, 0);
    lethalCross.setLineWidth(1f);
    lethalCross.setColorStroke(strokeColor);
    lethalCross.moveTo(0, HEALTH_RECT_SIZE);
    lethalCross.lineTo(HEALTH_RECT_SIZE, 0);
    lethalCross.stroke();
    return lethalCross;
  }

  public static PdfTemplate createAggravatedTemplate(PdfContentByte directContent, final Color strokeColor) {
    PdfTemplate aggravatedStar = directContent.createTemplate(HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    aggravatedStar.addTemplate(createLethalTemplate(directContent, strokeColor), 0, 0);
    aggravatedStar.setLineWidth(1f);
    aggravatedStar.setColorStroke(strokeColor);
    aggravatedStar.moveTo(HEALTH_RECT_SIZE / 2f, 0);
    aggravatedStar.lineTo(HEALTH_RECT_SIZE / 2f, HEALTH_RECT_SIZE);
    aggravatedStar.stroke();
    return aggravatedStar;
  }

  protected final PdfPCell createMovementCell(int value, int minValue) {
    return TableEncodingUtilities.createContentCellTable(
        Color.BLACK,
        String.valueOf(Math.max(value, minValue)),
        font,
        0.5f,
        Rectangle.BOX,
        Element.ALIGN_CENTER);
  }

  private void addHealthPenaltyCells(PdfPTable table, HealthLevelType level, IHealthyCharacter character) {
    final String healthPenText = penaltyTextByType.get(level);
    final Phrase healthPenaltyPhrase = new Phrase(healthPenText, font);
    PdfPCell healthPdfPCell = new TableCell(healthPenaltyPhrase, Rectangle.NO_BORDER);
    if (level == HealthLevelType.Incapacitated) {
      healthPdfPCell.setColspan(2);
      table.addCell(healthPdfPCell);
    }
    else {
      table.addCell(healthPdfPCell);
      String painToleranceText = " "; //$NON-NLS-1$
      if (character.hasPainTolerance()) {
        final int value = character.getPenalty(level);
        painToleranceText = "(" + (value == 0 ? "-" : "") + value + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
      }
      final TableCell painToleranceCell = new TableCell(new Phrase(painToleranceText, font), Rectangle.NO_BORDER);
      painToleranceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(painToleranceCell);
    }
  }

  protected final int getPenalty(HealthLevelType level, int painTolerance) {
    return Math.min(0, level.getIntValue() + painTolerance);
  }

  private void addHealthCells(
      PdfPTable table,
      IHealthyCharacter character,
      HealthLevelType level,
      int row,
      Image activeImage,
      Image passiveImage) {
    int naturalCount = getNaturalHealthLevels(level);
    if (row < naturalCount) {
      table.addCell(createHealthCell(activeImage));
    }
    else {
      addSpaceCells(table, 1);
    }
    int additionalCount = 9;
    if (level == HealthLevelType.Four) {
      addSpaceCells(table, 1);
      TableCell cell = new TableCell(new Phrase("Dying", commentFont), Rectangle.BOTTOM);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
      cell.setColspan(additionalCount - 1);
      table.addCell(cell);
      return;
    }
    if (level == HealthLevelType.Incapacitated) {
      addSpaceCells(table, 1);
      for (int index = 0; index < additionalCount - 1; index++) {
        if (index < character.getDyingLevels()) {
          table.addCell(createHealthCell(activeImage));
        }
        else {
          table.addCell(createHealthCell(passiveImage));
        }
      }
      return;
    }
    for (int index = 0; index < additionalCount; index++) {
      int value = naturalCount + row * additionalCount + index + 1;
      if (value <= character.getHealthLevelCount(level)) {
        table.addCell(createHealthCell(activeImage));
      }
      else {
        table.addCell(createHealthCell(passiveImage));
      }
    }
  }

  private int getNaturalHealthLevels(HealthLevelType level) {
    return level == HealthLevelType.One || level == HealthLevelType.Two ? 2 : 1;
  }

  private PdfPCell createHealthCell(Image image) {
    PdfPCell cell = new PdfPCell(image, false);
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
  }

  private float[] createColumnWidth() {
    float[] firstTwo = concat(MOVEMENT_COLUMNS, HEALTH_LEVEL_COLUMNS);
    return concat(firstTwo, HEALTH_COLUMNS);
  }

  private float[] concat(float[] first, float[] second) {
    float[] combined = new float[first.length + second.length];
    System.arraycopy(first, 0, combined, 0, first.length);
    System.arraycopy(second, 0, combined, first.length, second.length);
    return combined;
  }

  protected final Font getCommentFont() {
    return commentFont;
  }

  protected final void addMovementCells(PdfPTable table, HealthLevelType level, IHealthyCharacter character) {
    int moveValue = character.getMoveValue(level);
    table.addCell(createMovementCell(moveValue, 1));
    addSpaceCells(table, 1);
    table.addCell(createMovementCell(character.getDashValue(level), 2));
    int verticalJump = character.getVerticalJump(level);
    addSpaceCells(table, 1);
    table.addCell(createMovementCell(verticalJump * 2, 0));
    table.addCell(createMovementCell(verticalJump, 0));
  }
}