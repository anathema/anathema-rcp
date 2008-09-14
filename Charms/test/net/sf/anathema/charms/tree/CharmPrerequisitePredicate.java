/**
 * 
 */
package net.sf.anathema.charms.tree;

import net.disy.commons.core.predicate.IPredicate;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.charms.CharmPrerequisite;

public final class CharmPrerequisitePredicate implements IPredicate<CharmPrerequisite> {
  private final String destination;
  private final String source;

  public CharmPrerequisitePredicate(String destination, String source) {
    this.destination = destination;
    this.source = source;
  }

  @Override
  public boolean evaluate(CharmPrerequisite input) {
    return ObjectUtilities.equals(input.getDestination(), destination)
        && ObjectUtilities.equals(input.getSource(), source);
  }
}