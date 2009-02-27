package net.sf.anathema.character.trait.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.character.trait.collection.FavorizationTemplate;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;

public class FavorizationTemplateExtensionPoint implements IFavorizationTemplateMap {

  private static final String TAG_REQUIRED_FAVORED = "requiredFavored"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORED_COUNT = "favoredCount"; //$NON-NLS-1$
  private static final String ATTRIB_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private final IExtensionPoint extensionPoint;
  private final String modelId;

  public FavorizationTemplateExtensionPoint(final String modelId, final IExtensionPoint extensionPoint) {
    this.modelId = modelId;
    this.extensionPoint = extensionPoint;
  }

  public IFavorizationTemplate getTemplate(final String templateId) {
    final FavorizationTemplateElementPredicate elementPredicate = FavorizationTemplateElementPredicate.ForTemplateAndModelId(
        templateId,
        modelId);
    final IExtensionElement templateElement = extensionPoint.getFirst(elementPredicate);
    return templateElement == IExtensionElement.NO_ELEMENT
        ? new FavorizationTemplate(0)
        : readTemplate(templateElement);
  }

  private IFavorizationTemplate readTemplate(final IExtensionElement element) {
    final int favoredCount = readFavoredCount(element);
    final List<String> requiredFavored = readRequiredFavored(element);
    return new FavorizationTemplate(favoredCount, requiredFavored);
  }

  private int readFavoredCount(final IExtensionElement element) {
    return element.hasAttribute(ATTRIB_FAVORED_COUNT) ? element.getIntegerAttribute(ATTRIB_FAVORED_COUNT) : 0;
  }

  private List<String> readRequiredFavored(final IExtensionElement element) {
    final List<String> requiredFavored = new ArrayList<String>();
    for (final IExtensionElement traitReference : element.getElements(TAG_REQUIRED_FAVORED)) {
      requiredFavored.add(traitReference.getAttribute(ATTRIB_TRAIT_ID));
    }
    return requiredFavored;
  }
}