package net.sf.anathema.charms.character;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;

public class CharmsPersister implements IModelPersister<NullModelTemplate, ICharmModel> {

  @Override
  public ICharmModel createNew(NullModelTemplate template) {
    return new CharmModel();
  }

  @Override
  public ICharmModel load(Document document, NullModelTemplate template) throws PersistenceException {
    return new CharmModel();
  }

  @Override
  public void save(OutputStream stream, ICharmModel item) throws IOException, PersistenceException {
    Document document = new BundlePersistenceUtilities().createVersionedDocument("charms", IPluginConstants.PLUGIN_ID);
    DocumentUtilities.save(document, stream);
  }
}