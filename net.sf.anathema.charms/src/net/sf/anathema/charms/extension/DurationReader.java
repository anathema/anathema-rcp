package net.sf.anathema.charms.extension;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;

public class DurationReader {

  private static final String TAG_MINIMUM = "minimum"; //$NON-NLS-1$
  private static final String ATTRIB_KEYWORD = "keyword"; //$NON-NLS-1$
  private static final String TAG_TEXT = "text"; //$NON-NLS-1$
  private static final String TAG_ADDITION = "addition"; //$NON-NLS-1$
  private final DurationDto dto = new DurationDto();
  private final PrimitiveBuilder primitiveBuilder = new PrimitiveBuilder();

  public DurationDto read(IExtensionElement durationElement) {
    IExtensionElement onlyChild = durationElement.getElements()[0];
    if (isKeywordDuration(onlyChild)) {
      readKeywordDuration(onlyChild);
    }
    else if (isAdditiveDuration(onlyChild)) {
      readAdditionDuration(onlyChild);
    }
    else if (isMinimumDuration(onlyChild)) {
      readMinimumDuration(onlyChild);
    }
    return dto;
  }

  private void readMinimumDuration(IExtensionElement element) {
    buildContainedDurations(element);
    addBuiltPrimitivesTo(dto.minimums);
  }

  private boolean isMinimumDuration(IExtensionElement element) {
    return element.getName().equals(TAG_MINIMUM);
  }

  private void readAdditionDuration(IExtensionElement element) {
    buildContainedDurations(element);
    addBuiltPrimitivesTo(dto.additions);
  }

  private void addBuiltPrimitivesTo(List<PrimitiveDurationDto> additions) {
    additions.addAll(primitiveBuilder.getBuiltDurations());
  }

  private void buildContainedDurations(IExtensionElement element) {
    primitiveBuilder.read(element.getElements());
  }

  private boolean isAdditiveDuration(IExtensionElement element) {
    return element.getName().equals(TAG_ADDITION);
  }

  private boolean isKeywordDuration(IExtensionElement element) {
    return element.getName().equals(TAG_TEXT);
  }

  private void readKeywordDuration(IExtensionElement element) {
    dto.keyword = element.getAttribute(ATTRIB_KEYWORD);
  }
}