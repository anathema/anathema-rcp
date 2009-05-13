package net.sf.anathema.character.freebies.view;

import java.util.List;

import net.sf.anathema.character.core.character.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntry;

public interface IFreebiePointEntryFactory {

  public List<IValueEntry> create(ICharacterId id);
}