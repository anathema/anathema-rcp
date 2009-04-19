package net.sf.anathema.character.freebies.abilities.coverage;

import net.sf.anathema.character.trait.IBasicTrait;

public interface ICoverageBuilder {

  public int calculateCoverageForNextTrait(IBasicTrait trait);

}