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
  private final Element durationElement;
  private final XmlPrimitiveBuilder primitiveBuilder = new XmlPrimitiveBuilder();

  public XmlDurationReader(Element durationElement) {
    this.durationElement = durationElement;
  }

  public DurationDto read() {
    duration.keyword = durationElement.attributeValue(ATTRIB_DURATION);
    if (isAdditiveDuration()) {
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
    readPrimitiveChildren(elementName);
    addReadPrimitives(list);
  }

  private void addReadPrimitives(List<PrimitiveDurationDto> additions) {
    additions.addAll(primitiveBuilder.getBuiltPrimitives());
  }

  private void readPrimitiveChildren(String elementName) {
    for (Element element : ElementUtilities.elements(durationElement.element(elementName))) {
      primitiveBuilder.readSimpleDuration(element);
    }
  }

  private boolean isMinimumDuration() {
    return durationElement.element(TAG_MINIMUM) != null;
  }

  private boolean isAdditiveDuration() {
    return durationElement.element(TAG_ADDITION) != null;
  }
}