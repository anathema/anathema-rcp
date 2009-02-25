package net.sf.anathema.character.core.model.list;

import static net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother.*;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockName;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

@SuppressWarnings("nls")
public class ModelListElementObjectMother {

  public static IExtensionElement createDelegatingListElement(String modelListId, String referenceModelListId)
      throws ExtensionException {
    MockStringAttribute listIdAttrib = new MockStringAttribute("listId", referenceModelListId);
    IExtensionElement modelListReferenceElement = createExtensionElement(listIdAttrib, new MockName("listReference"));
    MockStringAttribute idAttrib = new MockStringAttribute("id", modelListId);
    return createExtensionElement(new MockChildren(modelListReferenceElement), idAttrib);
  }

  public static IExtensionElement createModelListElement(String listId, String modelId) throws ExtensionException {
    IExtensionElement modelReferenceElement = createModelReferenceElement(modelId);
    return createModelListElement(listId, modelReferenceElement);
  }

  public static IExtensionElement createModelReferenceElement(String modelId) throws ExtensionException {
    MockStringAttribute idAttribute = new MockStringAttribute("modelId", modelId);
    MockName nameAttrib = new MockName("modelReference");
    return ExtensionObjectMother.createExtensionElement(idAttribute, nameAttrib);
  }

  public static IExtensionElement createModelListElement(String listId, IExtensionElement... children)
      throws ExtensionException {
    MockStringAttribute modelIdAttrib = new MockStringAttribute("id", listId);
    return ExtensionObjectMother.createExtensionElement(new MockChildren(children), modelIdAttrib);
  }
}