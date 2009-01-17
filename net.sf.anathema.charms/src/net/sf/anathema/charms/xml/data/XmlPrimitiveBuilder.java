package net.sf.anathema.charms.xml.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.charms.data.duration.AmountDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;
import net.sf.anathema.charms.data.duration.TraitDto;
import net.sf.anathema.charms.extension.PrimitiveDurationFactory;

import org.dom4j.Element;

public class XmlPrimitiveBuilder {
  private final PrimitiveDurationFactory factory = new PrimitiveDurationFactory();
  private final List<PrimitiveDurationDto> durations = new ArrayList<PrimitiveDurationDto>();

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
    primitiveDuration.until = element.attributeValue("event");
  }

  private void readTraitDuration(Element element) {
    PrimitiveDurationDto primitiveDuration = createPrimitiveDuration();
    primitiveDuration.trait = new TraitDto();
    primitiveDuration.trait.trait = element.attributeValue("trait");
    primitiveDuration.trait.unit = element.attributeValue("unit");
    if (element.attribute("multiplier") != null) {
      primitiveDuration.trait.multiplier = Integer.valueOf(element.attributeValue("multiplier"));
    }
  }

  private void readAmountDuration(Element element) {
    PrimitiveDurationDto primitiveDuration = createPrimitiveDuration();
    primitiveDuration.amount = new AmountDto();
    primitiveDuration.amount.value = element.attributeValue("amount");
    primitiveDuration.amount.unit = element.attributeValue("unit");
  }

  private boolean isUntilDuration(Element element) {
    return element.attribute("event") != null;
  }

  private boolean isTraitDuration(Element element) {
    return element.attribute("trait") != null;
  }

  private boolean isAmountDuration(Element element) {
    return element.attribute("amount") != null;
  }

  public Collection< ? extends PrimitiveDurationDto> getBuiltPrimitives() {
    return durations;
  }
}