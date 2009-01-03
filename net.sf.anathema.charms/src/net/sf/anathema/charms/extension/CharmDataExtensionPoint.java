package net.sf.anathema.charms.extension;

import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmDataExtensionPoint implements ICharmDataMap {

  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String TAG_KEYWORD = "keyword"; //$NON-NLS-1$
  private static final String ATTRIB_CHARM_ID = "charmId"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_ID = "charmdata"; //$NON-NLS-1$
  private final IExtensionPoint extensionPoint;

  public CharmDataExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_POINT_ID));
  }

  public CharmDataExtensionPoint(IExtensionPoint extensionPoint) {
    this.extensionPoint = extensionPoint;
  }

  @Override
  public CharmDto getData(ICharmId charmId) {
    IExtensionElement extensionElement = getExtensionElement(charmId);
    CharmDto charmDto = createEmptyCharmData();
    if (extensionElement != null) {
      fillCharmData(extensionElement, charmDto);
    }
    return charmDto;
  }

  private IExtensionElement getExtensionElement(ICharmId charmId) {
    String idString = charmId.getId();
    AttributePredicate charmIdPredicate = AttributePredicate.FromNameAndValue(ATTRIB_CHARM_ID, idString);
    return extensionPoint.getFirst(charmIdPredicate);
  }

  private CharmDto createEmptyCharmData() {
    return new CharmDto();
  }

  private void fillCharmData(IExtensionElement extensionElement, CharmDto charmDto) {
    IExtensionElement typeElement = extensionElement.getElements()[0];
    charmDto.type = typeElement.getName();
    fillInKeywords(extensionElement, charmDto);
  }

  private void fillInKeywords(IExtensionElement extensionElement, CharmDto charmDto) {
    for (IExtensionElement keywordElement : extensionElement.getElements(TAG_KEYWORD)) {
      String keyWord = keywordElement.getAttribute(ATTRIB_VALUE);
      charmDto.keywords.add(keyWord);
    }
  }
}