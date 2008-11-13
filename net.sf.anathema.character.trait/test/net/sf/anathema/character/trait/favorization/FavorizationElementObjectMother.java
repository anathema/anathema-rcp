package net.sf.anathema.character.trait.favorization;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.basics.eclipse.extension.ExtensionException;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.fake.ExtensionObjectMother;
import net.sf.anathema.basics.eclipse.extension.fake.MockIntegerAttribute;
import net.sf.anathema.basics.eclipse.extension.fake.MockNamedChildren;
import net.sf.anathema.basics.eclipse.extension.fake.MockStringAttribute;

public class FavorizationElementObjectMother {

  public static final class RequiredFavoredElementTransformer implements ITransformer<String, IExtensionElement> {
    @Override
    public IExtensionElement transform(String traitId) {
      try {
        MockStringAttribute traitIdAttribute = new MockStringAttribute("traitId", traitId); //$NON-NLS-1$
        return ExtensionObjectMother.createExtensionElementWithAttributes(traitIdAttribute);
      }
      catch (ExtensionException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static IExtensionElement create(String templateId, String modelId, int favoredCount, String... requiredFavored)
      throws ExtensionException {
    MockStringAttribute templateIdAttribute = new MockStringAttribute("characterTemplateId", templateId); //$NON-NLS-1$
    MockStringAttribute modelIdAttribute = new MockStringAttribute("modelId", modelId); //$NON-NLS-1$
    MockIntegerAttribute countAttribute = new MockIntegerAttribute("favoredCount", favoredCount); //$NON-NLS-1$
    IExtensionElement[] requiredFavoredElements = ArrayUtilities.transform(
        requiredFavored,
        IExtensionElement.class,
        new RequiredFavoredElementTransformer());
    return ExtensionObjectMother.createExtensionElementWithAttributes(
        templateIdAttribute,
        modelIdAttribute,
        countAttribute,
        new MockNamedChildren("requiredFavored", requiredFavoredElements)); //$NON-NLS-1$
  }
}
