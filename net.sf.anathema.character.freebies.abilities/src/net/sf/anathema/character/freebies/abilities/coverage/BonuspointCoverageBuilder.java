package net.sf.anathema.character.freebies.abilities.coverage;

import net.sf.anathema.character.trait.IBasicTrait;

public class BonuspointCoverageBuilder implements ICoverageBuilder {

  private static final int FREEBIES_LIMIT = 3;
  private int remainingCheapFreebies;
  private int remainingExpensiveFreebies;

  public BonuspointCoverageBuilder(int remainingCheapFreebies, int remainingExpensiveFreebies) {
    this.remainingCheapFreebies = remainingCheapFreebies;
    this.remainingExpensiveFreebies = remainingExpensiveFreebies;
  }

  public int calculateCoverageForNextTrait(IBasicTrait trait) {
    int creationValue = trait.getCreationModel().getValue();
    int possiblyCovered = Math.min(creationValue, FREEBIES_LIMIT);
    boolean isCheap = trait.getStatusManager().getStatus().isCheap();
    int cheapFreebiesSpent = isCheap ? Math.min(remainingCheapFreebies, possiblyCovered) : 0;
    int expensiveFreebiesSpent = Math.min(remainingExpensiveFreebies, possiblyCovered - cheapFreebiesSpent);
    remainingCheapFreebies -= cheapFreebiesSpent;
    remainingExpensiveFreebies -= expensiveFreebiesSpent;
    return cheapFreebiesSpent + expensiveFreebiesSpent;
  }
}