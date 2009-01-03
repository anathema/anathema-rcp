package net.sf.anathema.charms.view.tooltipp;

import java.util.HashMap;
import java.util.Map;

public class DisplayResourceType {

  private final Map<String, String> shortNamesByType = new HashMap<String, String>();

  public DisplayResourceType() {
    shortNamesByType.put("motes", "m");
    shortNamesByType.put("willpower", "wp");
    shortNamesByType.put("bashing hl", "bhl");
    shortNamesByType.put("lethal hl", "lhl");
    shortNamesByType.put("aggrevated hl", "ahl");
    shortNamesByType.put("experience", "xp");
  }

  public String getShortName(String resourceType) {
    return shortNamesByType.get(resourceType);
  }
}