package net.sf.anathema.charms.character.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.disy.commons.core.creation.IFactory;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.charms.character.CharmCharacterPlugin;
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
  public CharmModel createNew(NullModelTemplate template) {
    return new CharmModel();
  }

  @Override
  public CharmModel load(Document document, NullModelTemplate template) throws PersistenceException {
    CharmModel charmModel = createNew(template);
    CharmModelMemento memento = new CharmModelMemento();
    load(document.getRootElement(), memento);
    charmModel.revertTo(memento);
    return charmModel;
  }

  private void load(Element parent, CharmModelMemento memento) {
    for (Element charmElement : ElementUtilities.elements(parent)) {
      boolean experienced = ElementUtilities.getBooleanAttribute(charmElement, ATTRIB_EXPERIENCED, false);
      List<ICharmId> idList = experienced ? memento.experienceLearnedCharms : memento.creationLearnedCharms;
      idList.add(loadCharmId(charmElement));
    }
  }

  private CharmId loadCharmId(Element charmElement) {
    String idPattern = charmElement.element(TAG_ID).getText();
    String trait = charmElement.element(TAG_TRAIT).getText();
    return new CharmId(idPattern, trait);
  }

  @Override
  public void save(OutputStream stream, ICharmModel item) throws IOException, PersistenceException {
    Document document = documentFactory.createInstance();
    Element rootElement = document.getRootElement();
    CharmModelMemento memento = item.getSaveState();
    // TODO vernünftiger Umgang mit der Fall, dass etwas sowohl experienced als auch creation learned sein kann?
    saveCharmIds(rootElement, memento.creationLearnedCharms, false);
    saveCharmIds(rootElement, memento.experienceLearnedCharms, true);
    DocumentUtilities.save(document, stream);
  }

  private void saveCharmIds(Element rootElement, List<ICharmId> charmIds, boolean experienced) {
    for (ICharmId charmId : charmIds) {
      saveCharm(rootElement, charmId, experienced);
    }
  }

  private void saveCharm(Element rootElement, ICharmId charmId, boolean experienced) {
    Element charmElement = rootElement.addElement(TAG_CHARM);
    ElementUtilities.addAttribute(charmElement, ATTRIB_EXPERIENCED, experienced);
    saveCharmId(charmElement, charmId);
  }

  private void saveCharmId(Element charmElement, ICharmId charmId) {
    charmElement.addElement(TAG_ID).addText(charmId.getIdPattern());
    charmElement.addElement(TAG_TRAIT).addText(charmId.getPrimaryTrait());
  }
}