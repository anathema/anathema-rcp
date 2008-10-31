package net.sf.anathema.character.trait.status;

import net.sf.anathema.lib.control.change.ChangeControl;

public class TraitStatusModel extends ChangeControl implements ITraitStatusModel {

  public ITraitStatus currentStatus = new DefaultStatus();

  @Override
  public ITraitStatus getStatus() {
    return currentStatus;
  }

  @Override
  public void setStatus(ITraitStatus status) {
    this.currentStatus = status;
    fireChangedEvent();
  }

  @Override
  public void toggleStatus() {
    if (!currentStatus.isModifiable()) {
      return;
    }
    setStatus(currentStatus instanceof DefaultStatus ? new FavoredStatus() : new DefaultStatus());
  }
}