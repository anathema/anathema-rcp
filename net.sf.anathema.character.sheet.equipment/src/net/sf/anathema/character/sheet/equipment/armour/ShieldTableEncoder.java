package net.sf.anathema.character.sheet.equipment.armour;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.equipment.armour.groups.CloseCombatShieldStatsGroup;
import net.sf.anathema.character.sheet.equipment.armour.groups.FatigueStatsGroup;
import net.sf.anathema.character.sheet.equipment.armour.groups.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.sheet.equipment.armour.groups.RangedCombatShieldStatsGroup;
import net.sf.anathema.character.sheet.equipment.stats.IArmourStats;
import net.sf.anathema.character.sheet.equipment.stats.IShieldStats;
import net.sf.anathema.character.sheet.equipment.weapons.groups.EquipmentNameStatsGroup;
import net.sf.anathema.character.sheet.stats.IStatsGroup;
import net.sf.anathema.character.sheet.table.AbstractFixedLineStatsTableEncoder;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;

public class ShieldTableEncoder extends AbstractFixedLineStatsTableEncoder<IShieldStats> {

  public ShieldTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, ICharacter character, Bounds bounds) {
    IStatsGroup<IShieldStats>[] groups = createStatsGroups(character);
    float[] columnWidths = calculateColumnWidths(groups);
    PdfPTable shieldTable = new PdfPTable(columnWidths);
    shieldTable.setTotalWidth(bounds.width);
    encodeContent(shieldTable, character, bounds);
    return shieldTable;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IShieldStats>[] createStatsGroups(ICharacter character) {
    return new IStatsGroup[] {
        new EquipmentNameStatsGroup<IArmourStats>(),
        new CloseCombatShieldStatsGroup(),
        new RangedCombatShieldStatsGroup(),
        new MobilityPenaltyStatsGroup(),
        new FatigueStatsGroup() };
  }

  @Override
  protected int getLineCount() {
    return 1;
  }

  @Override
  protected IShieldStats[] getPrintStats(ICharacter character) {
    return new IShieldStats[0];
  }
}