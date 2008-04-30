package net.sf.anathema.character.trait.collection;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IPluginExtension;
import net.sf.anathema.character.trait.model.IFavorizationTemplate;

public class FavorizationTemplateProvider {

  private static final String TAG_REQUIRED_FAVORED = "requiredFavored"; //$NON-NLS-1$
  private static final String ATTRIB_CHARACTER_TEMPLATE_ID = "characterTemplateId"; //$NON-NLS-1$
  private static final String ATTRIB_FAVORED_COUNT = "favoredCount"; //$NON-NLS-1$
  private static final String ATTRIB_MODEL_ID = "modelId"; //$NON-NLS-1$
  private static final String ATTRIB_TRAIT_ID = "traitId"; //$NON-NLS-1$
  private final IPluginExtension[] extensions;
  private final String modelId;

  public FavorizationTemplateProvider(String modelId, IPluginExtension... extensions) {
    this.modelId = modelId;
    this.extensions = extensions;
  }

  public IFavorizationTemplate getFavorizationTemplate(String characterTemplateId) {
    for (IPluginExtension extension : extensions) {
      for (IExtensionElement element : extension.getElements()) {
        String templateId = element.getAttribute(ATTRIB_CHARACTER_TEMPLATE_ID);
        String currentModelId = element.getAttribute(ATTRIB_MODEL_ID);
        if (templateId.equals(characterTemplateId) && currentModelId.equals(modelId)) {
          return createTemplate(element);
        }
      }
    }
    return new FavorizationTemplate(0);
  }

  private IFavorizationTemplate createTemplate(IExtensionElement element) {
    List<String> requiredFavored = new ArrayList<String>();
    int favoredCount = element.hasAttribute(ATTRIB_FAVORED_COUNT)
        ? element.getIntegerAttribute(ATTRIB_FAVORED_COUNT)
        : 0;
    for (IExtensionElement traitReference : element.getElements(TAG_REQUIRED_FAVORED)) {
      requiredFavored.add(traitReference.getAttribute(ATTRIB_TRAIT_ID));
    }
    return new FavorizationTemplate(favoredCount, requiredFavored);
  }
}