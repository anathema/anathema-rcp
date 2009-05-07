package net.sf.anathema.character.sheet.equipment.weapons;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.derivedtraits.AttackingCharacter;
import net.sf.anathema.character.sheet.equipment.stats.IWeaponStats;
import net.sf.anathema.character.sheet.equipment.weapons.groups.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.sheet.equipment.weapons.groups.DamageWeaponStatsGroup;
import net.sf.anathema.character.sheet.equipment.weapons.groups.DefenceWeaponStatsGroup;
import net.sf.anathema.character.sheet.equipment.weapons.groups.EquipmentNameStatsGroup;
import net.sf.anathema.character.sheet.equipment.weapons.groups.RangeWeaponStatsGroup;
import net.sf.anathema.character.sheet.equipment.weapons.groups.RateWeaponStatsGroup;
import net.sf.anathema.character.sheet.equipment.weapons.groups.SpeedWeaponStatsGroup;
import net.sf.anathema.character.sheet.equipment.weapons.groups.TagsStatsGroup;
import net.sf.anathema.character.sheet.stats.IStatsGroup;
import net.sf.anathema.character.sheet.table.AbstractFixedLineStatsTableEncoder;

import com.lowagie.text.pdf.BaseFont;

public class WeaponryTableEncoder extends AbstractFixedLineStatsTableEncoder<IWeaponStats> {

  public WeaponryTableEncoder(BaseFont baseFont) {
    super(baseFont);
  }

  @Override
  protected IStatsGroup<IWeaponStats>[] createStatsGroups(ICharacter character) {
    AttackingCharacter combatCharacter = new AttackingCharacter(character);
    return new IStatsGroup[] {
        new EquipmentNameStatsGroup(),
        new SpeedWeaponStatsGroup(),
        new AccuracyWeaponStatsGroup(combatCharacter),
        new DamageWeaponStatsGroup(combatCharacter),
        new DefenceWeaponStatsGroup(combatCharacter),
        new RateWeaponStatsGroup(),
        new RangeWeaponStatsGroup(),
        new TagsStatsGroup() };
  }

  @Override
  protected int getLineCount() {
    return 20;
  }

  @Override
  protected IWeaponStats[] getPrintStats(ICharacter character) {
    return new IWeaponStats[0];
  }
}