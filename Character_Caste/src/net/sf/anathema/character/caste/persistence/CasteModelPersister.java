package net.sf.anathema.character.caste.persistence;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.ICasteModel;
import net.sf.anathema.character.caste.plugin.ICastePluginConstants;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.character.core.model.template.IModelTemplate;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CasteModelPersister implements IModelPersister< IModelTemplate , ICasteModel> {

  private static final String ATTRIB_CASTE = "caste"; //$NON-NLS-1$
  private static final String TAG_MODEL = "casteModel"; //$NON-NLS-1$

  @Override
  public ICasteModel createNew(IModelTemplate template) {
    // TODO Hier können wir ein Template für die Unterschiedlichen Charactertypen einführen
    return new CasteModel();
  }

  @Override
  public ICasteModel load(Document document) throws PersistenceException {
    // TODO Richtiges Template laden
    ICasteModel casteModel = createNew(null);
    if (document == null) {
      return casteModel;
    }
    Element root = document.getRootElement();
    String caste = root.attributeValue(ATTRIB_CASTE);
    casteModel.setCaste(caste);
    return casteModel;
  }

  @Override
  public void save(OutputStream stream, ICasteModel item) throws IOException, PersistenceException {
    Document document = new BundlePersistenceUtilities().createVersionedDocument(
        TAG_MODEL,
        ICastePluginConstants.PLUGIN_ID);
    Element rootElement = document.getRootElement();
    save(rootElement, item);
    DocumentUtilities.save(document, stream);
  }

  protected void save(Element rootElement, ICasteModel item) {
    String caste = item.getCaste();
    if (caste != null) {
      rootElement.addAttribute(ATTRIB_CASTE, caste);
    }
  }
}