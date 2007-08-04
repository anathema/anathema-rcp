package net.sf.anathema.character.points.view;


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