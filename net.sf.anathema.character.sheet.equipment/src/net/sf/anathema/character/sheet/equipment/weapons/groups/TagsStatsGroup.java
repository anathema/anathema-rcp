package net.sf.anathema.character.sheet.equipment.weapons.groups;

import java.awt.Color;

import net.sf.anathema.character.sheet.equipment.stats.IWeaponStats;
import net.sf.anathema.character.sheet.equipment.stats.TagDto;
import net.sf.anathema.character.sheet.stats.IStatsGroup;
import net.sf.anathema.character.sheet.table.TableEncodingUtilities;
import net.sf.anathema.lib.lang.ConcatenateString;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public final class TagsStatsGroup implements IStatsGroup<IWeaponStats> {

  public int getColumnCount() {
    return 1;
  }

  public String getTitle() {
    return "Tags";
  }

  public Float[] getColumnWeights() {
    return new Float[] { new Float(1.7) };
  }

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null || weapon.getTags().length == 0) {
      table.addCell(createEmptyNameCell(font));
    }
    else {
      ConcatenateString concatenation = new ConcatenateString(","); //$NON-NLS-1$
      for (TagDto tag : weapon.getTags()) {
        concatenation.concatenate(tag.shortName);
      }
      String tagConcatenation = concatenation.create();
      table.addCell(createFilledContentCell(font, tagConcatenation));
    }
  }

  private PdfPCell createEmptyNameCell(Font font) {
    return createFilledContentCell(font, " "); //$NON-NLS-1$
  }

  private PdfPCell createFilledContentCell(Font font, final String text) {
    return TableEncodingUtilities.createContentCellTable(
        Color.BLACK,
        text,
        font,
        0.5f,
        Rectangle.BOTTOM,
        Element.ALIGN_LEFT);
  }
}