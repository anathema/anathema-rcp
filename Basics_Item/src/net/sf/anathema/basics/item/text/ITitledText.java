package net.sf.anathema.basics.item.text;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.lib.textualdescription.IStyledTextualDescription;
import net.sf.anathema.lib.textualdescription.ITextualDescription;

public interface ITitledText extends IItem {

  public ITextualDescription getName();

  public IStyledTextualDescription getContent();
}