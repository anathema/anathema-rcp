package net.sf.anathema.charms.character.sheet.generic;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.page.IVoidStateFormatConstants;
import net.sf.anathema.character.trait.display.IDisplayTrait;
import net.sf.anathema.character.trait.resources.TraitMessages;
import net.sf.anathema.charms.character.model.GenericCharmCollector;
import net.sf.anathema.charms.character.model.TraitCollector;
import net.sf.anathema.charms.character.sheet.AbstractTableEncoder;
import net.sf.anathema.charms.character.sheet.TableCell;
import net.sf.anathema.charms.character.sheet.TableEncodingUtilities;
import net.sf.anathema.charms.data.lookup.CharmNamesExtensionPoint;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTemplate;

public class GenericCharmTableEncoder extends AbstractTableEncoder {
  private final TraitMessages traitMessages = new TraitMessages();
  private final BaseFont baseFont;
  private final Iterable<String> genericIdPatterns;
  private final ICharacter character;

  public GenericCharmTableEncoder(BaseFont baseFont, Iterable<String> genericIdPatterns, ICharacter character) {
    this.baseFont = baseFont;
    this.genericIdPatterns = genericIdPatterns;
    this.character = character;
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, Bounds bounds) throws DocumentException {
    Font font = TableEncodingUtilities.createFont(baseFont);
    PdfTemplate learnedTemplate = createCharmDotTemplate(directContent, Color.BLACK);
    PdfTemplate notLearnedTemplate = createCharmDotTemplate(directContent, Color.WHITE);
    PdfPTable table = new PdfPTable(createColumnWidths());
    table.setWidthPercentage(100);
    table.addCell(new TableCell(new Phrase(), Rectangle.NO_BORDER));
    Collection<IDisplayTrait> traits = new TraitCollector(character).getAllTraits();
    for (IDisplayTrait trait : traits) {
      table.addCell(createHeaderCell(directContent, trait));
    }
    GenericCharmCollector collector = new GenericCharmCollector(character);
    CharmNamesExtensionPoint names = new CharmNamesExtensionPoint();
    for (String pattern : genericIdPatterns) {
      Phrase charmPhrase = new Phrase(names.getNameFor(new GenericDisplayId(character, pattern)), font);
      List<String> learnedTraits = collector.getTraits(pattern);
      table.addCell(new TableCell(charmPhrase, Rectangle.NO_BORDER));
      for (IDisplayTrait trait : traits) {
        table.addCell(createGenericCell(learnedTraits, trait, learnedTemplate, notLearnedTemplate));
      }
    }
    return table;
  }

  private PdfTemplate createCharmDotTemplate(PdfContentByte directContent, Color color) {
    float lineWidth = 0.75f;
    float templateSize = IVoidStateFormatConstants.SMALL_SYMBOL_HEIGHT - 1 + 2 * lineWidth;
    PdfTemplate template = directContent.createTemplate(templateSize, templateSize);
    template.setColorFill(color);
    template.setColorStroke(Color.BLACK);
    template.setLineWidth(lineWidth);
    float radius = templateSize / 2 - lineWidth;
    template.circle(templateSize / 2, templateSize / 2, radius);
    template.fillStroke();
    return template;
  }

  private PdfPCell createGenericCell(
      List<String> learnedTraits,
      IDisplayTrait type,
      PdfTemplate learnedTemplate,
      PdfTemplate notLearnedTemplate) throws DocumentException {
    boolean isLearned = learnedTraits.contains(type.getTraitType().getId());
    Image image = Image.getInstance(isLearned ? learnedTemplate : notLearnedTemplate);
    TableCell tableCell = new TableCell(image);
    tableCell.setPadding(0);
    return tableCell;
  }

  private PdfPCell createHeaderCell(PdfContentByte directContent, IDisplayTrait trait) throws DocumentException {
    directContent.setColorStroke(Color.BLACK);
    directContent.setColorFill(Color.BLACK);
    String text = traitMessages.getNameFor(trait.getTraitType().getId());
    float ascentPoint = baseFont.getAscentPoint(text, TableEncodingUtilities.FONT_SIZE);
    float descentPoint = baseFont.getDescentPoint(text, TableEncodingUtilities.FONT_SIZE);
    float templateWidth = baseFont.getWidthPoint(text, TableEncodingUtilities.FONT_SIZE);
    float templateHeight = ascentPoint - descentPoint;

    PdfTemplate template = directContent.createTemplate(templateWidth, templateHeight);
    template.beginText();
    template.setFontAndSize(baseFont, TableEncodingUtilities.FONT_SIZE);
    template.showTextAligned(Element.ALIGN_LEFT, text, 0, -descentPoint, 0);
    template.endText();
    Image image = Image.getInstance(template);
    image.setRotationDegrees(90);
    TableCell tableCell = new TableCell(image);
    tableCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    tableCell.setPaddingTop(5);
    return tableCell;
  }

  private float[] createColumnWidths() {
    float[] columnWidths = new float[26];
    Arrays.fill(columnWidths, 1);
    columnWidths[0] = 10;
    return columnWidths;
  }
}