package net.sf.anathema.charms.extension;

import net.sf.anathema.basics.eclipse.extension.AbstractExecutableExtension;
import net.sf.anathema.basics.eclipse.extension.AttributePredicate;
import net.sf.anathema.basics.eclipse.extension.EclipseExtensionPoint;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.basics.eclipse.extension.IExtensionPoint;
import net.sf.anathema.charms.IPluginConstants;
import net.sf.anathema.charms.data.CharmDto;
import net.sf.anathema.charms.data.ICharmDataMap;
import net.sf.anathema.charms.data.SourceDto;
import net.sf.anathema.charms.tree.ICharmId;

public class CharmDataExtensionPoint extends AbstractExecutableExtension implements ICharmDataMap {

  private static final String TAG_ADDITIONALDATA = "additionalData"; //$NON-NLS-1$
  private static final String ATTRIB_CHARM_ID = "charmId"; //$NON-NLS-1$
  private static final String TAG_KEYWORD = "keyword"; //$NON-NLS-1$
  private static final String ATTRIB_VALUE = "value"; //$NON-NLS-1$
  private static final String TAG_SOURCE = "source"; //$NON-NLS-1$
  private static final String ATTRIB_SOURCE = "source"; //$NON-NLS-1$
  private static final String ATTRIB_ADDITION = "addition"; //$NON-NLS-1$
  private static final String TAG_COST = "cost"; //$NON-NLS-1$
  private static final String EXTENSION_POINT_ID = "charmdata"; //$NON-NLS-1$
  private final IExtensionPoint extensionPoint;

  public CharmDataExtensionPoint() {
    this(new EclipseExtensionPoint(IPluginConstants.PLUGIN_ID, EXTENSION_POINT_ID));
  }

  private CharmDataExtensionPoint(IExtensionPoint extensionPoint) {
    this.extensionPoint = extensionPoint;
  }

  @Override
  public CharmDto getData(ICharmId charmId) {
    IExtensionElement extensionElement = getExtensionElement(charmId);
    if (extensionElement == null) {
      return null;
    }
    CharmDto charmDto = new CharmDto();
    fillCharmData(extensionElement, charmDto);
    return charmDto;
  }

  private IExtensionElement getExtensionElement(ICharmId charmId) {
    String idString = charmId.getId();
    AttributePredicate charmIdPredicate = AttributePredicate.FromNameAndValue(ATTRIB_CHARM_ID, idString);
    return extensionPoint.getFirst(charmIdPredicate);
  }

  private void fillCharmData(IExtensionElement extensionElement, CharmDto charmDto) {
    IExtensionElement typeElement = extensionElement.getElements()[0];
    charmDto.type = typeElement.getName();
    fillInCosts(typeElement, charmDto);
    fillInKeywords(extensionElement, charmDto);
    fillInSources(extensionElement, charmDto);
  }

  private void fillInCosts(IExtensionElement typeElement, CharmDto charmDto) {
    IExtensionElement additionalDataElement = typeElement.getElement(TAG_ADDITIONALDATA);
    if (additionalDataElement == null) {
      return;
    }
    CostReader costReader = new CostReader();
    for (IExtensionElement costElement : additionalDataElement.getElements(TAG_COST)) {
      charmDto.costs.add(costReader.read(costElement));
    }
  }

  private void fillInKeywords(IExtensionElement extensionElement, CharmDto charmDto) {
    for (IExtensionElement keywordElement : extensionElement.getElements(TAG_KEYWORD)) {
      String keyWord = keywordElement.getAttribute(ATTRIB_VALUE);
      charmDto.keywords.add(keyWord);
    }
  }

  private void fillInSources(IExtensionElement extensionElement, CharmDto charmDto) {
    for (IExtensionElement sourceElement : extensionElement.getElements(TAG_SOURCE)) {
      charmDto.sources.add(createSource(sourceElement));
    }
  }

  private SourceDto createSource(IExtensionElement sourceElement) {
    SourceDto sourceDto = new SourceDto();
    sourceDto.source = sourceElement.getAttribute(ATTRIB_SOURCE);
    sourceDto.addition = sourceElement.getAttribute(ATTRIB_ADDITION);
    return sourceDto;
  }
}