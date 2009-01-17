package net.sf.anathema.charms.xml.data;

import java.util.List;

import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class ComplexDurationReader implements IDurationReader {

  private static final String TAG_ADDITION = "addition"; //$NON-NLS-1$
  private static final String TAG_MINIMUM = "minimum"; //$NON-NLS-1$
  private final DurationDto duration = new DurationDto();
  private final XmlPrimitiveBuilder primitiveBuilder = new XmlPrimitiveBuilder();
  private final Element durationElement;

  public ComplexDurationReader(Element durationElement) {
    this.durationElement = durationElement;
  }

  public DurationDto read() {
    if (isAdditiveDuration()) {
      readAdditiveDuration();
    }
    else {
      readMinimumDuration();
    }
    return duration;
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

  private boolean isAdditiveDuration() {
    return durationElement.element(TAG_ADDITION) != null;
  }
}