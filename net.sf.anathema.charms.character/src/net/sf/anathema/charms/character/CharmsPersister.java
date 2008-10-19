package net.sf.anathema.charms.character;

import java.io.IOException;
import java.io.OutputStream;

import net.disy.commons.core.creation.IFactory;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CharmsPersister implements IModelPersister<NullModelTemplate, ICharmModel> {

  private static final String TAG_ID = "id"; //$NON-NLS-1$
  private static final String TAG_CHARM = "charm"; //$NON-NLS-1$
  private static final String TAG_CHARMS = "charms"; //$NON-NLS-1$
  private final IFactory<Document, RuntimeException> documentFactory;

  public CharmsPersister() {
    this(new IFactory<Document, RuntimeException>() {
      @Override
      public Document createInstance() {
        return new BundlePersistenceUtilities().createVersionedDocument(TAG_CHARMS, IPluginConstants.PLUGIN_ID);
      }
    });
  }

  public CharmsPersister(IFactory<Document, RuntimeException> factory) {
    this.documentFactory = factory;
  }

  @Override
  public ICharmModel createNew(NullModelTemplate template) {
    return new CharmModel();
  }

  @Override
  public ICharmModel load(Document document, NullModelTemplate template) throws PersistenceException {
    CharmModel charmModel = new CharmModel();
    for (Element charmElement : ElementUtilities.elements(document.getRootElement())) {
      charmModel.toggleLearned(charmElement.element(TAG_ID).getText());
    }
    return charmModel;
  }

  @Override
  public void save(OutputStream stream, ICharmModel item) throws IOException, PersistenceException {
    Document document = documentFactory.createInstance();
    Element rootElement = document.getRootElement();
    for (String charmId : item.getLearnedCharms()) {
      Element charmElement = rootElement.addElement(TAG_CHARM);
      charmElement.addElement(TAG_ID).addText(charmId);
    }
    DocumentUtilities.save(document, stream);
  }
}