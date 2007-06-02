package net.sf.anathema.character.description;

import net.sf.anathema.basics.item.IItem;
import net.sf.anathema.lib.textualdescription.ITextualDescription;

public interface ICharacterDescription extends IItem {

  public ITextualDescription getName();

  public ITextualDescription getPeriphrase();

  public ITextualDescription getCharacterization();

  public ITextualDescription getPhysicalDescription();

  public ITextualDescription getNotes();

  public ITextualDescription getPlayer();

  public ITextualDescription getConcept();
}