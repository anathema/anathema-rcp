package net.sf.anathema.charms.xml.data;

import net.sf.anathema.charms.data.duration.DurationDto;

import org.dom4j.Element;

public class SimpleDurationReader implements IDurationReader {
  private static final String ATTRIB_DURATION = "duration"; //$NON-NLS-1$
  private final XmlPrimitiveBuilder primitiveBuilder = new XmlPrimitiveBuilder();
  private final DurationDto duration = new DurationDto();
  private final Element durationElement;

  public SimpleDurationReader(Element durationElement) {
    this.durationElement = durationElement;
  }

  public DurationDto read() {
    if (isKeywordDuration()) {
      readKeywordDuration();
    }
    else {
      readPrimitiveDuration();
    }
    return duration;
  }

  private void readPrimitiveDuration() {
    primitiveBuilder.readSimpleDuration(durationElement);
    duration.additions.addAll(primitiveBuilder.getBuiltPrimitives());
  }

  private void readKeywordDuration() {
    duration.keyword = durationElement.attributeValue(ATTRIB_DURATION);
  }

  private boolean isKeywordDuration() {
    return durationElement.attributeValue(ATTRIB_DURATION) != null;
  }
}