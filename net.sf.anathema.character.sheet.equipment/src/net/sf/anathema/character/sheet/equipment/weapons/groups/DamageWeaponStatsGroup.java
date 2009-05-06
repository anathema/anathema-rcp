package net.sf.anathema.character.sheet.equipment.weapons.groups;

import net.sf.anathema.character.derivedtraits.CombatCharacter;
import net.sf.anathema.character.derivedtraits.DamageDto;
import net.sf.anathema.character.sheet.equipment.stats.IWeaponStats;
import net.sf.anathema.character.sheet.stats.IStatsGroup;
import net.sf.anathema.character.trait.sheet.FinalValueCell;
import net.sf.anathema.character.trait.sheet.IntermediaryValueCell;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class DamageWeaponStatsGroup implements IStatsGroup<IWeaponStats> {

  private final CombatCharacter character;

  public DamageWeaponStatsGroup(CombatCharacter character) {
    this.character = character;
  }

  @Override
  public Float[] getColumnWeights() {
    return new Float[] { 1.0f, 1.0f, 0.7f };
  }

  public int getColumnCount() {
    return 3;
  }

  public void addContent(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon == null) {
      table.addCell(IntermediaryValueCell.CreateEmpty(font));
      table.addCell(FinalValueCell.CreateEmpty(font));
      table.addCell(FinalValueCell.CreateEmpty(font));
    }
    else if (weapon.inflictsNoDamage()) {
      table.addCell(IntermediaryValueCell.CreateForValue(font, ((Integer) null)));
      table.addCell(FinalValueCell.CreateForValue(font, ((Integer) null)));
      table.addCell(FinalValueCell.CreateForValue(font, ((Integer) null)));
    }
    else {
      addWeaponBonusCell(table, font, weapon);
      addFinalValueCell(table, font, weapon);
      addDamageLabelCell(table, font, weapon);
    }
  }

  private void addWeaponBonusCell(PdfPTable table, Font font, IWeaponStats weapon) {
    if (weapon.getDamageAttribute() != null) {
      table.addCell(IntermediaryValueCell.CreateForValue(font, weapon.getDamage()));
    }
    else {
      table.addCell(IntermediaryValueCell.CreateForValue(font, null));
    }
  }

  private void addFinalValueCell(PdfPTable table, Font font, IWeaponStats weapon) {
    DamageDto damageDto = createDamageDto(weapon);
    int finalValue = character.getDamage(damageDto);
    table.addCell(FinalValueCell.CreateForValue(font, finalValue));
  }

  private DamageDto createDamageDto(IWeaponStats weapon) {
    DamageDto damageDto = new DamageDto();
    if (weapon.getDamageAttribute() != null) {
      damageDto.attribute = weapon.getDamageAttribute().getId();
    }
    damageDto.bonus = weapon.getDamage();
    return damageDto;
  }

  private void addDamageLabelCell(PdfPTable table, Font font, IWeaponStats weapon) {
    String damageLabel = weapon.getDamageType().shortName;
    PdfPCell damageLabelCell = FinalValueCell.CreateForText(font, damageLabel);
    damageLabelCell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(damageLabelCell);
  }

  @Override
  public String getTitle() {
    return "Damage";
  }
}