package net.sf.anathema.charms.xml.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.charms.data.duration.AmountDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;
import net.sf.anathema.charms.data.duration.TraitDto;
import net.sf.anathema.charms.extension.data.PrimitiveDurationFactory;

import org.dom4j.Element;

public class XmlPrimitiveBuilder {
  private static final String ATTRIB_AMOUNT = "amount"; //$NON-NLS-1$
  private static final String ATTRIB_MULTIPLIER = "multiplier"; //$NON-NLS-1$
  private static final String ATTRIB_UNIT = "unit"; //$NON-NLS-1$
  private static final String ATTRIB_TRAIT = "trait"; //$NON-NLS-1$
  private static final String ATTRIB_EVENT = "event"; //$NON-NLS-1$
  private final PrimitiveDurationFactory factory = new PrimitiveDurationFactory();
  private final List<PrimitiveDurationDto> durations = new ArrayList<PrimitiveDurationDto>();

  public void readSimpleDurations(List<Element> elements) {
    for (Element element : elements) {
      readSimpleDuration(element);
    }
  }

  public void readSimpleDuration(Element element) {
    if (isUntilDuration(element)) {
      readUntilDuration(element);
    }
    if (isAmountDuration(element)) {
      readAmountDuration(element);
    }
    else if (isTraitDuration(element)) {
      readTraitDuration(element);
    }
  }

  private PrimitiveDurationDto createPrimitiveDuration() {
    return factory.addPrimitiveDurationTo(durations);
  }

  private void readUntilDuration(Element element) {
    PrimitiveDurationDto primitiveDuration = createPrimitiveDuration();
    primitiveDuration.until = element.attributeValue(ATTRIB_EVENT);
  }

  private void readTraitDuration(Element element) {
    PrimitiveDurationDto primitiveDuration = createPrimitiveDuration();
    primitiveDuration.trait = new TraitDto();
    primitiveDuration.trait.trait = element.attributeValue(ATTRIB_TRAIT);
    primitiveDuration.trait.unit = element.attributeValue(ATTRIB_UNIT);
    if (hasMultiplier(element)) {
      primitiveDuration.trait.multiplier = Integer.valueOf(element.attributeValue(ATTRIB_MULTIPLIER));
    }
  }

  private boolean hasMultiplier(Element element) {
    return element.attribute(ATTRIB_MULTIPLIER) != null;
  }

  private void readAmountDuration(Element element) {
    PrimitiveDurationDto primitiveDuration = createPrimitiveDuration();
    primitiveDuration.amount = new AmountDto();
    primitiveDuration.amount.value = element.attributeValue(ATTRIB_AMOUNT);
    primitiveDuration.amount.unit = element.attributeValue(ATTRIB_UNIT);
  }

  private boolean isUntilDuration(Element element) {
    return element.attribute(ATTRIB_EVENT) != null;
  }

  private boolean isTraitDuration(Element element) {
    return element.attribute(ATTRIB_TRAIT) != null;
  }

  private boolean isAmountDuration(Element element) {
    return element.attribute(ATTRIB_AMOUNT) != null;
  }

  public Collection< ? extends PrimitiveDurationDto> getBuiltPrimitives() {
    return durations;
  }
}