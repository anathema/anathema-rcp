package net.sf.anathema.character.backgrounds;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.disy.commons.core.creation.IFactory;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class BackgroundPersister implements IModelPersister<BackgroundTemplate, IBackgroundModel> {

  private static final String TAG_BACKGROUNDS = "backgrounds"; //$NON-NLS-1$
  private static final String TAG_BACKGROUND = "background"; //$NON-NLS-1$
  private final IFactory<Document, RuntimeException> documentFactory;

  public BackgroundPersister() {
    this(new IFactory<Document, RuntimeException>() {
      @Override
      public Document createInstance() {
        return new BundlePersistenceUtilities().createVersionedDocument(
            TAG_BACKGROUNDS,
            BackgroundPluginConstants.PLUGIN_ID);
      }
    });
  }

  public BackgroundPersister(IFactory<Document, RuntimeException> documentFactory) {
    this.documentFactory = documentFactory;
  }

  @Override
  public IBackgroundModel createNew(BackgroundTemplate template) {
    return new BackgroundModel();
  }

  @Override
  public IBackgroundModel load(Document document, BackgroundTemplate template) throws PersistenceException {
    BackgroundModel model = new BackgroundModel();
    Element parent = document.getRootElement();
    List<Element> backgroundElements = ElementUtilities.elements(parent, TAG_BACKGROUND);
    for (Element element : backgroundElements) {
      model.addBackground(element.getText());
    }
    return model;
  }

  @Override
  public void save(OutputStream stream, IBackgroundModel model) throws IOException, PersistenceException {
    Document document = documentFactory.createInstance();
    Element parent = document.getRootElement();
    for (String background : model.getBackgrounds()) {
      parent.addElement(TAG_BACKGROUND).setText(background);
    }
    DocumentUtilities.save(document, stream);
  }
}