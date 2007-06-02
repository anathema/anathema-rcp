package net.sf.anathema.basics.item.data;

import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextualDescription;

public interface IBasicItemData extends IItemData {

  public ITextualDescription getName();

  public IStyledTextualDescription getContent();
}