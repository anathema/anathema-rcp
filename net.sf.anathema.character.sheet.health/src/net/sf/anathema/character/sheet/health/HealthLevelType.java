package net.sf.anathema.character.sheet.health;

import net.sf.anathema.lib.util.IIdentificate;

public enum HealthLevelType implements IIdentificate {

  Zero("0", 0), One("1", -1), Two("2", -2), Four("4", -4), Incapacitated("Incapacitated", Integer.MIN_VALUE);

  private final String id;
  private final int value;

  public String getId() {
    return id;
  }

  private HealthLevelType(String id, int value) {
    this.id = id;
    this.value = value;
  }

  @Override
  public String toString() {
    return getId();
  }

  public int getIntValue() {
    return value;
  }
}