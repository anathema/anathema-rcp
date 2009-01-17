package net.sf.anathema.charms.xml.data;

import java.util.List;

import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class XmlDurationReader {

  private static final String ATTRIB_DURATION = "duration"; //$NON-NLS-1$
  private static final String TAG_ADDITION = "addition"; //$NON-NLS-1$
  private static final String TAG_MINIMUM = "minimum"; //$NON-NLS-1$
  private final DurationDto duration = new DurationDto();
  private final XmlPrimitiveBuilder primitiveBuilder = new XmlPrimitiveBuilder();
  private final Element durationElement;

  public XmlDurationReader(Element durationElement) {
    this.durationElement = durationElement;
  }

  public DurationDto read() {
    if (isKeywordDuration()) {
      readKeywordDuration();
    }
    else if (isAdditiveDuration()) {
      readAdditiveDuration();
    }
    else if (isMinimumDuration()) {
      readMinimumDuration();
    }
    else {
      readSimpleDuration();
    }
    return duration;
  }

  private boolean isKeywordDuration() {
    return durationElement.attributeValue(ATTRIB_DURATION) != null;
  }

  private void readKeywordDuration() {
    duration.keyword = durationElement.attributeValue(ATTRIB_DURATION);
  }

  private void readSimpleDuration() {
    primitiveBuilder.readSimpleDuration(durationElement);
    duration.additions.addAll(primitiveBuilder.getBuiltPrimitives());
  }

  private void readMinimumDuration() {
    buildAndAddPrimitives(TAG_MINIMUM, duration.minimums);
  }

  private void readAdditiveDuration() {
    buildAndAddPrimitives(TAG_ADDITION, duration.additions);
  }

  private void buildAndAddPrimitives(String elementName, List<PrimitiveDurationDto> list) {
    Element listElement = durationElement.element(elementName);
    readPrimitiveChildren(listElement);
    addReadPrimitives(list);
  }

  private void addReadPrimitives(List<PrimitiveDurationDto> additions) {
    additions.addAll(primitiveBuilder.getBuiltPrimitives());
  }

  private void readPrimitiveChildren(Element listElement) {
    primitiveBuilder.readSimpleDurations(ElementUtilities.elements(listElement));
  }

  private boolean isMinimumDuration() {
    return durationElement.element(TAG_MINIMUM) != null;
  }

  private boolean isAdditiveDuration() {
    return durationElement.element(TAG_ADDITION) != null;
  }
}