package net.sf.anathema.charms.character.combo;

import java.util.List;

import net.disy.commons.core.util.CollectionUtilities;
import net.sf.anathema.charms.data.CharmDto;

public class ComboCharm {

  private final CharmDto data;

  public ComboCharm(CharmDto data) {
    this.data = data;
  }

  public List<String> getComboKeywords() {
    return CollectionUtilities.filter(data.keywords, new IsComboKeyword());
  }
  
  public boolean hasComboKeyword() {
    return getComboKeywords().size() > 0;
  }
  
  public boolean isComboBasic() {
    return getComboKeywords().contains("Combo-Basic");
  }
  
  public boolean isComboOk() {
    return getComboKeywords().contains("Combo-OK");
  }
  
  public boolean isReflexive() {
    return "reflexive".equals(String.valueOf(data.type).toLowerCase());
  }
  
  public boolean isSimple() {
    return "simple".equals(String.valueOf(data.type).toLowerCase());
  }
  
  public boolean isExtraAction() {
    return "extraaction".equals(String.valueOf(data.type).toLowerCase());
  }
}