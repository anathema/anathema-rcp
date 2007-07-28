package net.sf.anathema.character.points;

public final class UpdateRunnable implements Runnable {
  private final IUpdateable updateable;

  public UpdateRunnable(IUpdateable updateable) {
    this.updateable = updateable;
  }

  @Override
  public void run() {
    updateable.update();
  }
}