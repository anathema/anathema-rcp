package net.sf.anathema.basics.repository.input;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.basics.item.data.IBasicItemData;

public class ItemNameProvider {
  private final String untitledName;

  public ItemNameProvider(String untitledName) {
    this.untitledName = untitledName;
  }

  public String getName(IItem<IBasicItemData> item) {
    if (item == null) {
      return null;
    }
    String name = item.getItemData().getDescription().getName().getText();
    if (StringUtilities.isNullOrEmpty(name)) {
      name = untitledName;
    }
    return name;
  }
}
