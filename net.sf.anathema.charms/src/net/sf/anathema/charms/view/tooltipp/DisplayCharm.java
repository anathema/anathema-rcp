package net.sf.anathema.charms.view.tooltipp;

import net.sf.anathema.charms.data.CharmDto;

public class DisplayCharm {

  private final CharmDto data;
  private final String notAvailable;

  public DisplayCharm(CharmDto data) {
    this.data = data;
    this.notAvailable = "N/A";
  }

  public String getType() {
    String definedType = data.type;
    return definedType == null ? notAvailable : definedType;
  }
}