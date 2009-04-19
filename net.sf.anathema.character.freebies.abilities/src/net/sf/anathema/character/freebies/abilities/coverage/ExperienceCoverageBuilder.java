package net.sf.anathema.character.freebies.abilities.coverage;

import net.sf.anathema.character.trait.IBasicTrait;

public class ExperienceCoverageBuilder implements ICoverageBuilder {

  @Override
  public int calculateCoverageForNextTrait(IBasicTrait trait) {
    return trait.getCreationModel().getValue();
  }
}