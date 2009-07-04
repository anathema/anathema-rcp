package net.sf.anathema.charms.extension.data;

import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;

public class DurationReader {

  private static final String TAG_MINIMUM = "minimum"; //$NON-NLS-1$
  private static final String ATTRIB_KEYWORD = "keyword"; //$NON-NLS-1$
  private static final String TAG_TEXT = "text"; //$NON-NLS-1$
  private static final String TAG_ADDITION = "addition"; //$NON-NLS-1$
  private final DurationDto duration = new DurationDto();
  private final PrimitiveBuilder primitiveBuilder = new PrimitiveBuilder();
  private final IExtensionElement contentElement;

  public DurationReader(IExtensionElement durationElement) {
    this.contentElement = durationElement.getElements()[0];
  }

  public DurationDto read() {
    if (isKeywordDuration()) {
      readKeywordDuration();
    }
    else if (isAdditiveDuration()) {
      readAdditionDuration();
    }
    else if (isMinimumDuration()) {
      readMinimumDuration();
    }
    return duration;
  }

  private void readMinimumDuration() {
    buildContainedDurations();
    addBuiltPrimitivesTo(duration.minimums);
  }

  private boolean isMinimumDuration() {
    return contentElement.getName().equals(TAG_MINIMUM);
  }

  private void readAdditionDuration() {
    buildContainedDurations();
    addBuiltPrimitivesTo(duration.additions);
  }

  private void addBuiltPrimitivesTo(List<PrimitiveDurationDto> additions) {
    additions.addAll(primitiveBuilder.getBuiltDurations());
  }

  private void buildContainedDurations() {
    primitiveBuilder.read(contentElement.getElements());
  }

  private boolean isAdditiveDuration() {
    return contentElement.getName().equals(TAG_ADDITION);
  }

  private boolean isKeywordDuration() {
    return contentElement.getName().equals(TAG_TEXT);
  }

  private void readKeywordDuration() {
    duration.keyword = contentElement.getAttribute(ATTRIB_KEYWORD);
  }
}