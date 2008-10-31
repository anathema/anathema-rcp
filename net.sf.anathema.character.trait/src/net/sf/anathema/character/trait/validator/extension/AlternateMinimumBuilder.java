package net.sf.anathema.character.trait.validator.extension;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.predicate.ICheck;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.validator.IValidator;

public class AlternateMinimumBuilder {
  
  private final Map<String, MinimumRequirement> requirements = new HashMap<String, MinimumRequirement>();

  public void addAlternative(IBasicTrait trait, int value) {
    requirements.put(trait.getTraitType().getId(), new MinimumRequirement(trait, value));
  }

  public void reset() {
    requirements.clear();
  }

  public IValidator createValidator(IBasicTrait trait) {
    String traitId = trait.getTraitType().getId();
    if (!requirements.containsKey(traitId)) {
      return null;
    }
    MinimumRequirement requirement = requirements.get(traitId);
    requirements.remove(traitId);
    Collection<? extends ICheck> otherChecks = requirements.values();
    return new ValidateAlternateMinimalValue(requirement.getMinimalValue(), otherChecks);
  }
}