package net.sf.anathema.charms.providing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.charms.data.duration.AmountDto;
import net.sf.anathema.charms.data.duration.PrimitiveDurationDto;
import net.sf.anathema.charms.data.duration.TraitDto;

public class PrimitiveBuilder {

  private static final String TAG_AMOUNT = "amount"; //$NON-NLS-1$
  private static final String TAG_UNTIL = "until"; //$NON-NLS-1$
  private static final String ATTRIB_UNIT = "unit"; //$NON-NLS-1$
  private static final String ATTRIB_MULTIPLIER = "multiplier"; //$NON-NLS-1$
  private static final String ATTRIB_TRAIT = "trait"; //$NON-NLS-1$
  private static final Object TAG_TRAIT = "trait"; //$NON-NLS-1$
  private final PrimitiveDurationFactory factory = new PrimitiveDurationFactory();
  private final List<PrimitiveDurationDto> durations = new ArrayList<PrimitiveDurationDto>();

  public void read(IExtensionElement[] elements) {
    for (IExtensionElement element : elements) {
      readElement(element);
    }
  }

  private void readElement(IExtensionElement element) {
    if (isUntilDuration(element)) {
      readUntilDuration(element);
    }
    else if (isTraitDuration(element)) {
      readTraitDuration(element);
    }
    else if (isAmountDuration(element)) {
      readAmountDuration(element);
    }
  }

  private boolean isAmountDuration(IExtensionElement element) {
    return element.getName().equals(TAG_AMOUNT);
  }

  private boolean isTraitDuration(IExtensionElement element) {
    return element.getName().equals(TAG_TRAIT);
  }

  private boolean isUntilDuration(IExtensionElement element) {
    return element.getName().equals(TAG_UNTIL);
  }

  private void readTraitDuration(IExtensionElement element) {
    PrimitiveDurationDto primitiveDuration = createPrimitiveDuration();
    primitiveDuration.trait = new TraitDto();
    primitiveDuration.trait.trait = element.getAttribute(ATTRIB_TRAIT);
    primitiveDuration.trait.multiplier = element.getIntegerAttribute(ATTRIB_MULTIPLIER);
    primitiveDuration.trait.unit = element.getAttribute(ATTRIB_UNIT);
  }

  private void readAmountDuration(IExtensionElement element) {
    PrimitiveDurationDto primitiveDuration = createPrimitiveDuration();
    primitiveDuration.amount = new AmountDto();
    primitiveDuration.amount.unit = element.getAttribute(ATTRIB_UNIT);
    primitiveDuration.amount.value = element.getAttribute("value"); //$NON-NLS-1$
  }

  private void readUntilDuration(IExtensionElement element) {
    PrimitiveDurationDto primitiveDuration = createPrimitiveDuration();
    primitiveDuration.until = element.getAttribute("event"); //$NON-NLS-1$
  }

  public Collection<PrimitiveDurationDto> getBuiltDurations() {
    return durations;
  }

  private PrimitiveDurationDto createPrimitiveDuration() {
    return factory.addPrimitiveDurationTo(durations);
  }

}