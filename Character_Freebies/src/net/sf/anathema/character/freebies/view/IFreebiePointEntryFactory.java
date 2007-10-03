package net.sf.anathema.character.freebies.view;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntry;

public interface IFreebiePointEntryFactory {

  public IValueEntry[] create(ICharacterId id);
}