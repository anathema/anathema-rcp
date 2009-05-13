package net.sf.anathema.character.caste.persistence;

import java.io.IOException;
import java.io.OutputStream;

import net.sf.anathema.basics.item.persistence.BundlePersistenceUtilities;
import net.sf.anathema.character.caste.ICasteModel;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.caste.plugin.ICastePluginConstants;
import net.sf.anathema.character.core.model.IModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;
import org.dom4j.Element;

public class CasteModelPersister implements IModelPersister<CasteTemplate, ICasteModel> {

  private static final String ATTRIB_CASTE = "caste"; //$NON-NLS-1$
  private static final String TAG_MODEL = "casteModel"; //$NON-NLS-1$

  @Override
  public CasteModel createNew(CasteTemplate template) {
    return new CasteModel(template);
  }

  @Override
  public ICasteModel load(Document document, CasteTemplate template) throws PersistenceException {
    CasteModel casteModel = createNew(template);
    if (document != null) {
      setCasteFromDocument(document, casteModel);
    }
    return casteModel;
  }

  private void setCasteFromDocument(Document document, CasteModel casteModel) {
    Element root = document.getRootElement();
    String caste = root.attributeValue(ATTRIB_CASTE);
    casteModel.setCasteById(caste);
  }

  @Override
  public void save(OutputStream stream, ICasteModel item) throws IOException, PersistenceException {
    Document document = createCasteDocument(item.getCaste());
    DocumentUtilities.save(document, stream);
  }

  public Document createCasteDocument(IIdentificate caste) {
    Document document = new BundlePersistenceUtilities().createDocument(
        TAG_MODEL,
        ICastePluginConstants.PLUGIN_ID);
    Element rootElement = document.getRootElement();
    save(rootElement, caste);
    return document;
  }

  protected void save(Element rootElement, IIdentificate caste) {
    if (caste != null) {
      rootElement.addAttribute(ATTRIB_CASTE, caste.getId());
    }
  }
}