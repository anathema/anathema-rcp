package net.sf.anathema.character.sheet.equipment.armour;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.sheet.elements.Bounds;
import net.sf.anathema.character.sheet.equipment.armour.groups.FatigueStatsGroup;
import net.sf.anathema.character.sheet.equipment.armour.groups.HardnessStatsGroup;
import net.sf.anathema.character.sheet.equipment.armour.groups.IArmourStatsGroup;
import net.sf.anathema.character.sheet.equipment.armour.groups.MobilityPenaltyStatsGroup;
import net.sf.anathema.character.sheet.equipment.armour.groups.SoakArmourStatsGroup;
import net.sf.anathema.character.sheet.equipment.stats.IArmourStats;
import net.sf.anathema.character.sheet.equipment.weapons.groups.EquipmentNameStatsGroup;
import net.sf.anathema.character.sheet.stats.IStatsGroup;
import net.sf.anathema.character.sheet.table.AbstractFixedLineStatsTableEncoder;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;

public class ArmourTableEncoder extends AbstractFixedLineStatsTableEncoder<IArmourStats> {

  public ArmourTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  @Override
  protected PdfPTable createTable(PdfContentByte directContent, ICharacter character, Bounds bounds) {
    PdfPTable armourTable = super.createTable(directContent, character, bounds);
    IArmourStats totalArmour = new NullArmourStats();
    IStatsGroup<IArmourStats>[] groups = createStatsGroups(character);
    for (int index = 0; index < groups.length; index++) {
      if (index != 0) {
        armourTable.addCell(createSpaceCell());
      }
      IStatsGroup<IArmourStats> group = groups[index];
      if (group instanceof IArmourStatsGroup) {
        ((IArmourStatsGroup) group).addTotal(armourTable, getFont(), totalArmour);
      }
      else {
        group.addContent(armourTable, getFont(), totalArmour);
      }
    }
    return armourTable;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IArmourStats>[] createStatsGroups(ICharacter character) {
    return new IStatsGroup[] {
        new EquipmentNameStatsGroup<IArmourStats>(),
        new SoakArmourStatsGroup(),
        new HardnessStatsGroup(),
        new MobilityPenaltyStatsGroup(),
        new FatigueStatsGroup() };
  }

  @Override
  protected int getLineCount() {
    return 3;
  }

  @Override
  protected IArmourStats[] getPrintStats(ICharacter character) {
    return new IArmourStats[0];
  }
}