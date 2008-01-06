package net.sf.anathema.character.caste.persistence;

import static org.junit.Assert.*;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.caste.model.ICasteModel;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

public class CasteModelPersisterTest {

  private static final String CASTE = "EvilTwin"; //$NON-NLS-1$
  private CasteModelPersister persister;
  private CasteModel casteModel;
  private Element rootElement;

  @Before
  public void createPersister() {
    persister = new CasteModelPersister();
    casteModel = new CasteModel(new CasteTemplate(CASTE));
    rootElement = DocumentHelper.createElement("root"); //$NON-NLS-1$
  }

  @Test
  public void casteIsSavedInAttributeCaste() throws Exception {
    casteModel.setCaste("EvilTwin"); //$NON-NLS-1$
    persister.save(rootElement, casteModel);
    assertEquals("EvilTwin", rootElement.attributeValue("caste")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void nothingIsSavedForNullCaste() throws Exception {
    casteModel.setCaste(null);
    persister.save(rootElement, casteModel);
    assertNull(rootElement.attributeValue("caste")); //$NON-NLS-1$ 
  }

  @Test
  public void casteIsReloaded() throws Exception {
    casteModel.setCaste("EvilTwin"); //$NON-NLS-1$
    persister.save(rootElement, casteModel);
    ICasteModel loadedCasteModel = persister.load(DocumentHelper.createDocument(rootElement), new CasteTemplate(CASTE));
    assertNotSame(casteModel, loadedCasteModel);
    assertEquals(casteModel.getCaste(), loadedCasteModel.getCaste());
  }
}