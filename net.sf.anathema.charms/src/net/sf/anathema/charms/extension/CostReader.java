package net.sf.anathema.charms.extension;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.data.cost.BaseDto;
import net.sf.anathema.charms.data.cost.CostDto;
import net.sf.anathema.charms.data.cost.LinearDto;
import net.sf.anathema.charms.data.cost.ResourceDto;

public class CostReader {

  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$
  private static final String TAG_RESOURCE = "resource"; //$NON-NLS-1$
  private static final String TAG_BASE = "base"; //$NON-NLS-1$
  private static final String ATTRIB_AMOUNT = "amount"; //$NON-NLS-1$
  private static final String ATTRIB_OR_MORE = "orMore"; //$NON-NLS-1$
  private static final String TAG_LINEAR = "linear"; //$NON-NLS-1$
  private static final String ATTRIB_UNIT = "unit"; //$NON-NLS-1$

  public CostDto read(IExtensionElement costElement) {
    CostDto costDto = new CostDto();
    for (IExtensionElement resourceElement : costElement.getElements(TAG_RESOURCE)) {
      costDto.resources.add(readResource(resourceElement));
    }
    return costDto;
  }

  private ResourceDto readResource(IExtensionElement resourceElement) {
    ResourceDto dto = new ResourceDto();
    dto.type = resourceElement.getAttribute(ATTRIB_TYPE);
    dto.baseDto = readBaseAmount(resourceElement);
    dto.linearDto = readLinearAmount(resourceElement);
    return dto;
  }

  private BaseDto readBaseAmount(IExtensionElement resourceElement) {
    IExtensionElement baseElement = resourceElement.getElement(TAG_BASE);
    if (baseElement == null) {
      return null;
    }
    BaseDto dto = new BaseDto();
    dto.amount = baseElement.getIntegerAttribute(ATTRIB_AMOUNT);
    dto.orMore = baseElement.getBooleanAttribute(ATTRIB_OR_MORE);
    return dto;
  }

  private LinearDto readLinearAmount(IExtensionElement resourceElement) {
    IExtensionElement baseElement = resourceElement.getElement(TAG_LINEAR);
    if (baseElement == null) {
      return null;
    }
    LinearDto dto = new LinearDto();
    dto.amount = baseElement.getIntegerAttribute(ATTRIB_AMOUNT);
    dto.unit = baseElement.getAttribute(ATTRIB_UNIT);
    return dto;
  }
}