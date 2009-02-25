package net.sf.anathema.character.core.model.list;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import static net.sf.anathema.character.core.model.list.ModelListElementObjectMother.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;

import org.junit.Test;

@SuppressWarnings("nls")
public class ModelListProvider_Test {

  private static final String OTHER_MODEL_ID = "other.model.id";
  private static final String MODEL_LIST_ID = "my.model.list"; //$NON-NLS-1$
  private static final String DELEGATING_MODEL_LIST_ID = "my.delegating.model.list"; //$NON-NLS-1$
  private static final String MODEL_ID = "expected.model.id"; //$NON-NLS-1$

  private ModelListProvider createProvider(IExtensionElement... listElements) {
    return new ModelListProvider(createPluginExtension(listElements));
  }

  @Test
  public void containsModelListWithModelId() throws Exception {
    IExtensionElement listElement = createModelListElement(MODEL_LIST_ID, MODEL_ID);
    ModelListProvider modelListProvider = createProvider(listElement);
    assertTrue(modelListProvider.getModelList(MODEL_LIST_ID).contains(MODEL_ID));
  }

  @Test
  public void containsDelegatingModelListWithModelId() throws Exception {
    IExtensionElement listElement = createModelListElement(MODEL_LIST_ID, MODEL_ID);
    IExtensionElement delegatingListElement = createDelegatingListElement(DELEGATING_MODEL_LIST_ID, MODEL_LIST_ID);
    ModelListProvider modelListProvider = createProvider(listElement, delegatingListElement);
    assertTrue(modelListProvider.getModelList(DELEGATING_MODEL_LIST_ID).contains(MODEL_ID));
  }

  @Test
  public void returnsEmptyModelListForUnknownModelListId() throws Exception {
    IExtensionElement listElement = createModelListElement(MODEL_LIST_ID, MODEL_ID);
    ModelListProvider modelListProvider = createProvider(listElement);
    assertFalse(modelListProvider.getModelList("Nonsens").contains(MODEL_ID));
  }

  @Test
  public void combinesModelListsForSameId() throws Exception {
    IExtensionElement firstElement = createModelListElement(MODEL_LIST_ID, MODEL_ID);
    IExtensionElement secondElement = createModelListElement(MODEL_LIST_ID, OTHER_MODEL_ID);
    ModelListProvider listProvider = createProvider(firstElement, secondElement);
    IModelList modelList = listProvider.getModelList(MODEL_LIST_ID);
    assertThat(modelList.contains(MODEL_ID), is(true));
    assertThat(modelList.contains(OTHER_MODEL_ID), is(true));
  }
}