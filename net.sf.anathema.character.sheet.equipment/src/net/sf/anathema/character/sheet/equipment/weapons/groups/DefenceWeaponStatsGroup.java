package net.sf.anathema.character.sheet.equipment.weapons.groups;

import net.sf.anathema.character.attributes.model.IAttributeIds;
import net.sf.anathema.character.derivedtraits.AttackDto;
import net.sf.anathema.character.derivedtraits.CombatCharacter;
import net.sf.anathema.character.sheet.equipment.stats.IWeaponStats;
import net.sf.anathema.character.trait.sheet.AbstractValueStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;
import net.sf.anathema.character.trait.sheet.IntermediaryValueCell;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;

public class DefenceWeaponStatsGroup extends AbstractValueStatsGroup<IWeaponStats> {

  private final CombatCharacter character;

  public DefenceWeaponStatsGroup(CombatCharacter character) {
    this.character = character;
  }

  @Override
  public String getTitle() {
    return "Defence";
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
      table.addCell(IntermediaryValueCell.CreateForValue(font, weapon.getDefence()));
      table.addCell(FinalValueCell.CreateForValue(font, getDefenceValue(weapon)));
    }
  }

  private Integer getDefenceValue(IWeaponStats weapon) {
    if (weapon.getDefence() == null) {
      return null;
    }
    AttackDto parryDto = createParryDto(weapon);
    return character.getDefenceValue(parryDto);
  }

  private AttackDto createParryDto(IWeaponStats weapon) {
    AttackDto parryDto = new AttackDto();
    parryDto.ability = weapon.getAbility().getId();
    parryDto.attribute = IAttributeIds.DEXTERITY;
    parryDto.bonus = weapon.getDefence();
    return parryDto;
  }
}