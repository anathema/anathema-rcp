package net.sf.anathema.character.experience;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Document;

public class ExperienceModelPersister implements IModelPersister<IExperienceModel> {

  @Override
  public IExperienceModel load(Document document) throws PersistenceException {
    return createNew();
  }

  @Override
  public void save(OutputStream stream, IExperienceModel item) throws IOException, PersistenceException {
    // nothing to do
  }

  @Override
  public IExperienceModel createNew() {
    return new ExperienceModel();
  }
}