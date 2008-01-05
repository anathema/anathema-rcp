package net.sf.anathema.character.core.model.list;

import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

import org.junit.Before;
import org.junit.Test;

public class ModelListProviderTest {

  private static final String CONTAINED_MODEL_LIST_ID = "my.model.list"; //$NON-NLS-1$
  private static final String CONTAINED_MODEL_ID = "expected.model.id"; //$NON-NLS-1$
  private ModelListProvider modelListProvider;

  @Before
  public void createFilledProvider() throws ExtensionException {
    IExtensionElement modelReferenceElement = ExtensionObjectMother.createExtensionElementWithAttributes(
        new MockStringAttribute("modelId", CONTAINED_MODEL_ID), //$NON-NLS-1$
        new MockName("modelReference")); //$NON-NLS-1$
    IExtensionElement modelListElement = ExtensionObjectMother.createExtensionElementWithAttributes(
        new MockChildren(modelReferenceElement),
        new MockStringAttribute("id", CONTAINED_MODEL_LIST_ID)); //$NON-NLS-1$
    modelListProvider = new ModelListProvider(ExtensionObjectMother.createPluginExtension(modelListElement));
  }

  @Test
  public void containsModelListWithModelId() throws Exception {
    assertTrue(modelListProvider.getModelList(CONTAINED_MODEL_LIST_ID).contains(CONTAINED_MODEL_ID));
  }
}