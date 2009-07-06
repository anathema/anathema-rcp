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
}