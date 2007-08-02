package net.sf.anathema.character.points.model;

public final class UpdateRunnable implements Runnable {
  private final IUpdatable[] allUpdateable;

  public UpdateRunnable(IUpdatable... updateable) {
    this.allUpdateable = updateable;
  }

  @Override
  public void run() {
    for (IUpdatable updateable : this.allUpdateable) {
      updateable.update();
    }
  }
}