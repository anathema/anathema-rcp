package net.sf.anathema.character.freebies;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntry;

public class FreebiePointEntryFactory implements IFreebiePointEntryFactory {

  @Override
  public IValueEntry[] create(ICharacterId id) {
    return new IValueEntry[0];
  }
}