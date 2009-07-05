package net.sf.anathema.charms.character.combo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import net.disy.commons.core.creation.IFactory;
import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.NullModelTemplate;
import net.sf.anathema.charms.character.CharmCharacterPlugin;
import net.sf.anathema.charms.character.model.CharmIdPersister;
import net.sf.anathema.charms.tree.CharmId;
import net.sf.anathema.charms.tree.ICharmId;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class ComboPersister implements IModelPersister<NullModelTemplate, IComboModel> {

  private static final String ATTRIB_EXPERIENCED = "experienced"; //$NON-NLS-1$
  private static final String TAG_DESCRIPTION = "description"; //$NON-NLS-1$
  private static final String TAG_NAME = "name"; //$NON-NLS-1$
  private static final String TAG_CHARM = "charm"; //$NON-NLS-1$
  private static final String TAG_COMBO = "combo"; //$NON-NLS-1$
  private static final String TAG_COMBOS = "combos"; //$NON-NLS-1$
  private final IFactory<Document, RuntimeException> documentFactory;
  private final CharmIdPersister charmIdPersister = new CharmIdPersister();

  public ComboPersister() {
    this(new IFactory<Document, RuntimeException>() {
      @Override
      public Document createInstance() {
        return new BundlePersistenceUtilities().createDocument(TAG_COMBOS, CharmCharacterPlugin.PLUGIN_ID);
      }
    });
  }

  public ComboPersister(IFactory<Document, RuntimeException> factory) {
    documentFactory = factory;
  }

  @Override
  public ComboModel createNew(NullModelTemplate template) {
    return new ComboModel();
  }

  @Override
  public IComboModel load(Document document, NullModelTemplate template) throws PersistenceException {
    ComboModel model = createNew(template);
    Object memento = loadMemento(document);
    model.revertTo(memento);
    return model;
  }

  protected Object loadMemento(Document document) {
    ComboModelMemento memento = new ComboModelMemento();
    load(document.getRootElement(), memento);
    return memento;
  }

  private void load(Element parent, ComboModelMemento memento) {
    for (Element comboElement : ElementUtilities.elements(parent)) {
      boolean experienced = ElementUtilities.getBooleanAttribute(comboElement, ATTRIB_EXPERIENCED, false);
      List<Combo> comboList = experienced ? memento.experienceLearned : memento.creationLearned;
      comboList.add(loadCombo(comboElement));
    }
  }

  private Combo loadCombo(Element comboElement) {
    Combo combo = new Combo();
    combo.name = loadText(comboElement, TAG_NAME);
    combo.description = loadText(comboElement, TAG_DESCRIPTION);
    loadCharms(combo, comboElement);
    return combo;
  }

  private String loadText(Element rootElement, String tag) {
    Element element = rootElement.element(tag);
    return element == null ? null : element.getText();
  }

  private void loadCharms(Combo combo, Element comboElement) {
    for (Element charmElement : ElementUtilities.elements(comboElement, TAG_CHARM)) {
      CharmId charmId = charmIdPersister.load(charmElement);
      combo.charms.add(charmId);
    }
  }

  @Override
  public void save(OutputStream stream, IComboModel item) throws IOException, PersistenceException {
    Document document = documentFactory.createInstance();
    Element rootElement = document.getRootElement();
    ComboModelMemento memento = item.getSaveState();
    saveComboList(rootElement, memento.creationLearned, false);
    saveComboList(rootElement, memento.experienceLearned, true);
    DocumentUtilities.save(document, stream);
  }

  private void saveComboList(Element parent, List<Combo> combos, boolean experienced) {
    for (Combo combo : combos) {
      saveCombo(parent, combo, experienced);
    }
  }

  private void saveCombo(Element parent, Combo combo, boolean experienced) {
    Element comboElement = parent.addElement(TAG_COMBO);
    comboElement.addAttribute(ATTRIB_EXPERIENCED, String.valueOf(experienced));
    saveText(comboElement, TAG_NAME, combo.name);
    saveText(comboElement, TAG_DESCRIPTION, combo.description);
    saveCharms(comboElement, combo.charms);
  }

  private void saveText(Element parent, String tag, String value) {
    if (value == null) {
      return;
    }
    parent.addElement(tag).addText(value);
  }

  private void saveCharms(Element comboElement, List<ICharmId> charms) {
    for (ICharmId charmId : charms) {
      Element charmElement = comboElement.addElement(TAG_CHARM);
      charmIdPersister.save(charmElement, charmId);
    }
  }
}