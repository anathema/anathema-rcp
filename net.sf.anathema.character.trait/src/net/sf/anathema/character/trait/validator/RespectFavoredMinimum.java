package net.sf.anathema.character.trait.validator;

import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.status.FavoredStatus;

public class RespectFavoredMinimum implements IValidator {

  private final IBasicTrait basicTrait;

  public RespectFavoredMinimum(IBasicTrait basicTrait) {
    this.basicTrait = basicTrait;
  }

  @Override
  public int getValidValue(int value) {
    boolean isFavored = basicTrait.getStatusManager().getStatus() instanceof FavoredStatus;
    return Math.max(isFavored ? 1 : 0, value);
  }
}