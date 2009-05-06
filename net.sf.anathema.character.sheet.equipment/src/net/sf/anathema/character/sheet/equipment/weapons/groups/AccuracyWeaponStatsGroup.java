package net.sf.anathema.character.sheet.equipment.weapons.groups;

import net.sf.anathema.character.attributes.model.IAttributesPluginConstants;
import net.sf.anathema.character.derivedtraits.AttackDto;
import net.sf.anathema.character.derivedtraits.CombatCharacter;
import net.sf.anathema.character.sheet.equipment.weapons.stats.IWeaponStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;
import net.sf.anathema.character.trait.sheet.IntermediaryValueCell;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class AccuracyWeaponStatsGroup extends AbstractValueStatsGroup<IWeaponStats> {

  private final CombatCharacter character;

  public AccuracyWeaponStatsGroup(CombatCharacter character) {
    this.character = character;
  }

  @Override
  public String getTitle() {
    return "Accuracy";
  }

  public int getColumnCount() {
    return 2;
  }

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(IntermediaryValueCell.CreateEmpty(font));
      table.addCell(FinalValueCell.CreateEmpty(font));
    }
    else {
      int weaponValue = weapon.getAccuracy();
      table.addCell(IntermediaryValueCell.CreateForValue(font, weaponValue));
      AttackDto attackDto = createAttackDto(weapon);
      int attackValue = character.getAttackValue(attackDto);
      table.addCell(FinalValueCell.CreateForValue(font, attackValue));
    }
  }

  private AttackDto createAttackDto(IWeaponStats weapon) {
    AttackDto attackDto = new AttackDto();
    attackDto.ability = weapon.getAbility().getId();
    attackDto.attribute = IAttributesPluginConstants.DEXTERITY_ID;
    attackDto.bonus = weapon.getAccuracy();
    return attackDto;
  }
}