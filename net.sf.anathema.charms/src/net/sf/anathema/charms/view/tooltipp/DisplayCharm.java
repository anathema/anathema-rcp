package net.sf.anathema.charms.view.tooltipp;

import net.sf.anathema.charms.data.CharmDto;

public class DisplayCharm {

  private final CharmDto data;
  private final String notAvailable;
  private final String none;

  public DisplayCharm(CharmDto data) {
    this.data = data;
    this.notAvailable = "N/A";
    this.none = "-";
  }

  public String getType() {
    String definedType = data.type;
    return definedType == null ? notAvailable : definedType;
  }

  public String getKeywords() {
    StringBuilder keywordsBuilder = new StringBuilder(", ");
    for (String keyword : data.keywords) {
      keywordsBuilder.add(keyword);
    }
    return keywordsBuilder.create(none);
  }
}