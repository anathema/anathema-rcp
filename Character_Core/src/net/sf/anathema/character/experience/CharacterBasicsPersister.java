package net.sf.anathema.character.experience;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;

public class CharacterBasicsPersister implements IModelPersister<ICharacterBasics> {

  @Override
  public ICharacterBasics load(Document document) throws PersistenceException {
    return createNew();
  }

  @Override
  public void save(OutputStream stream, ICharacterBasics item) throws IOException, PersistenceException {
    // nothing to do
  }

  @Override
  public ICharacterBasics createNew() {
    return new CharacterBasics();
  }
}