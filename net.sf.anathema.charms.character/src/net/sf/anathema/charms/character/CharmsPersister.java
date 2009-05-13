package net.sf.anathema.charms.character;

import java.io.IOException;
import java.io.OutputStream;

import net.disy.commons.core.creation.IFactory;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.charms.character.model.CharmModel;
import net.sf.anathema.charms.character.model.ICharmModel;
import net.sf.anathema.charms.character.plugin.CharmCharacterPlugin;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CharmsPersister implements IModelPersister<NullModelTemplate, ICharmModel> {

  private static final String ATTRIB_EXPERIENCED = "experienced"; //$NON-NLS-1$
  private static final String TAG_ID = "id"; //$NON-NLS-1$
  private static final String TAG_TRAIT = "trait"; //$NON-NLS-1$
  private static final String TAG_CHARM = "charm"; //$NON-NLS-1$
  private static final String TAG_CHARMS = "charms"; //$NON-NLS-1$
  private final IFactory<Document, RuntimeException> documentFactory;

  public CharmsPersister() {
    this(new IFactory<Document, RuntimeException>() {
      @Override
      public Document createInstance() {
        return new BundlePersistenceUtilities().createDocument(TAG_CHARMS, CharmCharacterPlugin.PLUGIN_ID);
      }
    });
  }

  public CharmsPersister(IFactory<Document, RuntimeException> factory) {
    documentFactory = factory;
  }

  @Override
  public ICharmModel createNew(NullModelTemplate template) {
    return new CharmModel();
  }

  @Override
  public ICharmModel load(Document document, NullModelTemplate template) throws PersistenceException {
    CharmModel charmModel = new CharmModel();
    for (Element charmElement : ElementUtilities.elements(document.getRootElement())) {
      boolean experienced = ElementUtilities.getBooleanAttribute(charmElement, ATTRIB_EXPERIENCED, false);
      if (experienced) {
        charmModel.toggleExperiencedLearned(loadCharmId(charmElement));
      }
      else {
        charmModel.toggleCreationLearned(loadCharmId(charmElement));
      }
    }
    return charmModel;
  }

  private CharmId loadCharmId(Element charmElement) {
    String idPattern = charmElement.element(TAG_ID).getText();
    String trait = charmElement.element(TAG_TRAIT).getText();
    return new CharmId(idPattern, trait);
  }

  @Override
  public void save(OutputStream stream, ICharmModel item) throws IOException, PersistenceException {
    // TODO vernünftiger Umgang mit der Fall, dass etwas sowohl experienced als auch creation learned sein kann?
    Document document = documentFactory.createInstance();
    Element rootElement = document.getRootElement();
    for (ICharmId charmId : item.getCreationLearnedCharms()) {
      Element charmElement = rootElement.addElement(TAG_CHARM);
      ElementUtilities.addAttribute(charmElement, ATTRIB_EXPERIENCED, false);
      savaeCharmId(charmElement, charmId);
    }
    for (ICharmId charmId : item.getExperienceLearnedCharms()) {
      Element charmElement = rootElement.addElement(TAG_CHARM);
      ElementUtilities.addAttribute(charmElement, ATTRIB_EXPERIENCED, true);
      savaeCharmId(charmElement, charmId);
    }
    DocumentUtilities.save(document, stream);
  }

  private void savaeCharmId(Element charmElement, ICharmId charmId) {
    charmElement.addElement(TAG_ID).addText(charmId.getIdPattern());
    charmElement.addElement(TAG_TRAIT).addText(charmId.getPrimaryTrait());
  }
}