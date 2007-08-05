package net.sf.anathema.character.points.view;

import net.sf.anathema.character.core.model.ICharacterId;
import net.sf.anathema.view.valuelist.IValueEntryFactory;

public interface ICharacterValueEntryFactory extends IValueEntryFactory {

  public ICharacterId getCharacterId();
}