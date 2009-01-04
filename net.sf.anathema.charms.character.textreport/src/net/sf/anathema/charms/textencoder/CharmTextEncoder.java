package net.sf.anathema.charms.textencoder;

import java.util.ArrayList;

import net.sf.anathema.character.core.character.ICharacter;
import net.sf.anathema.character.textreport.encoder.AbstractTextEncoder;
import net.sf.anathema.charms.character.model.ICharmModel;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;

public class CharmTextEncoder extends AbstractTextEncoder {

  
  public Iterable<Element> createParagraphs(ICharacter character) throws DocumentException {
    ICharmModel model = (ICharmModel) character.getModel(ICharmModel.MODEL_ID);
    Chunk title = createBoldTitle("Charms:");
    // TODO Auto-generated method stub
    return new ArrayList<Element>();
  }
}
