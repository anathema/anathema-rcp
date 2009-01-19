package net.sf.anathema.basics.repository.input.internal;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.basics.item.text.ITitledText;

public class ItemNameProvider {
  private final String untitledName;

  public ItemNameProvider(String untitledName) {
    this.untitledName = untitledName;
  }

  public String getName(ITitledText itemData) {
    if (itemData == null) {
      return ""; //$NON-NLS-1$
    }
    String name = itemData.getName().getText();
    if (StringUtilities.isNullOrEmpty(name)) {
      name = untitledName;
    }
    return name;
  }
}