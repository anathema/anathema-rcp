package net.sf.anathema.character.trait.validator.extension;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.basics.eclipse.extension.IExtensionElement;
import net.sf.anathema.character.trait.IBasicTrait;

public final class HasTraitId implements IPredicate<IExtensionElement> {
  private final IBasicTrait trait;

  public HasTraitId(IBasicTrait trait) {
    this.trait = trait;
  }

  @Override
  public boolean evaluate(IExtensionElement element) {
    String traitIdAttribute = element.getAttribute(IValidatorXmlConstants.ATTRIB_TRAIT_ID);
    String traitType = trait.getTraitType().getId();
    return traitIdAttribute == null || traitType.equals(traitIdAttribute);
  }
}