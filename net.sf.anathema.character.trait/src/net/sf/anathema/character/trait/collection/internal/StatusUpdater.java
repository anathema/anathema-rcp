package net.sf.anathema.character.trait.collection.internal;

import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.trait.IBasicTrait;

public final class StatusUpdater implements IChangeListener {
  private final IBasicTrait subTrait;
  private final IBasicTrait basicTrait;

  public StatusUpdater(IBasicTrait subTrait, IBasicTrait basicTrait) {
    this.subTrait = subTrait;
    this.basicTrait = basicTrait;
  }

  @Override
  public void stateChanged() {
    subTrait.getStatusManager().setStatus(basicTrait.getStatusManager().getStatus());
  }
}