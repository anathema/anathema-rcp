package net.sf.anathema.character.caste.persistence;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.character.caste.CasteObjectMother;
import net.sf.anathema.character.caste.model.CasteModel;
import net.sf.anathema.character.caste.model.CasteTemplate;
import net.sf.anathema.character.caste.model.ICaste;
import net.sf.anathema.character.caste.model.ICasteModel;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

public class CasteModelPersisterTest {

  private static final String CASTE_ID = "EvilTwin"; //$NON-NLS-1$
  private CasteModelPersister persister;
  private CasteModel casteModel;
  private Element rootElement;
  private ICaste caste;

  @Before
  public void createPersister() throws ExtensionException {
    persister = new CasteModelPersister();
    caste = CasteObjectMother.createCaste(CASTE_ID, null);
    casteModel = new CasteModel(new CasteTemplate(caste));
    rootElement = DocumentHelper.createElement("root"); //$NON-NLS-1$
  }

  @Test
  public void casteIsSavedInAttributeCaste() throws Exception {
    casteModel.setCasteById(CASTE_ID);
    persister.save(rootElement, casteModel);
    assertEquals(CASTE_ID, rootElement.attributeValue("caste")); //$NON-NLS-1$
  }

  @Test
  public void nothingIsSavedForNullCaste() throws Exception {
    casteModel.setCasteById(null);
    persister.save(rootElement, casteModel);
    assertNull(rootElement.attributeValue("caste")); //$NON-NLS-1$ 
  }

  @Test
  public void casteIsReloaded() throws Exception {
    casteModel.setCasteById("EvilTwin"); //$NON-NLS-1$
    persister.save(rootElement, casteModel);
    ICasteModel loadedCasteModel = persister.load(DocumentHelper.createDocument(rootElement), new CasteTemplate(caste));
    assertNotSame(casteModel, loadedCasteModel);
    assertEquals(casteModel.getCaste(), loadedCasteModel.getCaste());
  }
}