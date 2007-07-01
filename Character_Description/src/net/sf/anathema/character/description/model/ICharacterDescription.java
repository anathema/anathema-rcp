package net.sf.anathema.character.description.model;

import net.sf.anathema.character.core.model.IModel;
import net.sf.anathema.lib.textualdescription.ITextualDescription;

public interface ICharacterDescription extends IModel {

  public static String MODEL_ID = "net.sf.anathema.character.description.model"; //$NON-NLS-1$

  public ITextualDescription getName();

  public ITextualDescription getPeriphrasis();

  public ITextualDescription getCharacterization();

  public ITextualDescription getPhysicalDescription();

  public ITextualDescription getNotes();

  public ITextualDescription getPlayer();

  public ITextualDescription getConcept();
}