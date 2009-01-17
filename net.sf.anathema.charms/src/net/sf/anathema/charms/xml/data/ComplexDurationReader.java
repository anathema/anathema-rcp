package net.sf.anathema.charms.xml.data;

import java.util.List;

import net.sf.anathema.charms.data.duration.DurationDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Element;

public class ComplexDurationReader implements IDurationReader {

  private static final String VALUE_ADDITION = "addition"; //$NON-NLS-1$
  private static final String ATTRIB_TYPE = "type"; //$NON-NLS-1$
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
    buildAndAddPrimitives(duration.minimums);
  }

  private void readAdditiveDuration() {
    buildAndAddPrimitives(duration.additions);
  }

  private void buildAndAddPrimitives(List<PrimitiveDurationDto> list) {
    readPrimitiveChildren();
    addReadPrimitives(list);
  }

  private void addReadPrimitives(List<PrimitiveDurationDto> additions) {
    additions.addAll(primitiveBuilder.getBuiltPrimitives());
  }

  private void readPrimitiveChildren() {
    primitiveBuilder.readSimpleDurations(ElementUtilities.elements(durationElement));
  }

  private boolean isAdditiveDuration() {
    return durationElement.attributeValue(ATTRIB_TYPE).equals(VALUE_ADDITION);
  }
}