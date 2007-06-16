package net.sf.anathema.character.attributes;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.ISingleFileItemPersister;
import net.sf.anathema.character.core.trait.Trait;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.Identificate;

import org.dom4j.Document;

public class AttributesPersister implements ISingleFileItemPersister<IAttributes> {

  @Override
  public IAttributes load(Document document) throws PersistenceException {
    IAttributes attributes = createNew();
    attributes.setClean();
    return attributes;
  }

  @Override
  public void save(OutputStream stream, IAttributes item) throws IOException, PersistenceException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public IAttributes createNew() {
    return new Attributes(new Trait(new Identificate("Has�")), new Trait(new Identificate("Jupp")));
  }
}