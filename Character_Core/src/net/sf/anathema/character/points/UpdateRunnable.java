package net.sf.anathema.character.points;

public final class UpdateRunnable implements Runnable {
  private final IUpdateable[] allUpdateable;

  public UpdateRunnable(IUpdateable... updateable) {
    this.allUpdateable = updateable;
  }

  @Override
  public void run() {
    for (IUpdateable updateable : this.allUpdateable) {
      updateable.update();
    }
  }
}