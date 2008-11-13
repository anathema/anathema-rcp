package net.sf.anathema.character.trait.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.character.trait.collection.FavorizationTemplate;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;

public class FavorizationTemplateExtensionPoint {

  private static final String TAG_REQUIRED_FAVORED = "requiredFavored"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORED_COUNT = "favoredCount"; //$NON-NLS-1$
  private static final String ATTRIB_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private final IExtensionPoint extensionPoint;
  private final String modelId;

  public FavorizationTemplateExtensionPoint(String modelId, IExtensionPoint extensionPoint) {
    this.modelId = modelId;
    this.extensionPoint = extensionPoint;
  }

  public IFavorizationTemplate readTemplate(final String templateId) {
    FavorizationTemplateElementPredicate elementPredicate = FavorizationTemplateElementPredicate.ForTemplateAndModelId(templateId, modelId);
    IExtensionElement templateElement = extensionPoint.getFirst(elementPredicate);
    return templateElement == null ? new FavorizationTemplate(0) : readTemplate(templateElement);
  }

  private IFavorizationTemplate readTemplate(IExtensionElement element) {
    int favoredCount = readFavoredCount(element);
    List<String> requiredFavored = readRequiredFavored(element);
    return new FavorizationTemplate(favoredCount, requiredFavored);
  }

  private int readFavoredCount(IExtensionElement element) {
    return element.hasAttribute(ATTRIB_FAVORED_COUNT) ? element.getIntegerAttribute(ATTRIB_FAVORED_COUNT) : 0;
  }

  private List<String> readRequiredFavored(IExtensionElement element) {
    List<String> requiredFavored = new ArrayList<String>();
    for (IExtensionElement traitReference : element.getElements(TAG_REQUIRED_FAVORED)) {
      requiredFavored.add(traitReference.getAttribute(ATTRIB_TRAIT_ID));
    }
    return requiredFavored;
  }
}