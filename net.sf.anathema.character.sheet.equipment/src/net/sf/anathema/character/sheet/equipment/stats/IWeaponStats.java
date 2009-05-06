package net.sf.anathema.character.sheet.equipment.stats;

import net.sf.anathema.lib.util.IIdentificate;

public interface IWeaponStats extends IEquipmentStats {

  public int getAccuracy();

  public int getDamage();

  public HealthTypeDto getDamageType();

  public int getSpeed();

  public TagDto[] getTags();

  public IIdentificate getAbility();

  public Integer getDefence();

  public Integer getRange();

  public Integer getRate();

  public IIdentificate getDamageAttribute();

  public boolean inflictsNoDamage();

  public boolean isRangedCombat();

  public IEquipmentStats[] getViews();
}