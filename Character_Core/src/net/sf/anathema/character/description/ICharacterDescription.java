package net.sf.anathema.character.description;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.textualdescription.ITextualDescription;

public interface ICharacterDescription {

  public ITextualDescription getName();

  public ITextualDescription getPeriphrase();

  public ITextualDescription getCharacterization();

  public ITextualDescription getPhysicalDescription();

  public ITextualDescription getNotes();

  public ITextualDescription getPlayer();

  public ITextualDescription getConcept();
  
  public void addOverallChangeListener(IObjectValueChangedListener<String> listener);
}