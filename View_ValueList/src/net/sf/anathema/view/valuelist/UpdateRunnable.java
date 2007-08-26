package net.sf.anathema.view.valuelist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.lib.ui.IUpdatable;

public final class UpdateRunnable implements Runnable {
  private List<IUpdatable> allUpdateable = new ArrayList<IUpdatable>();

  public UpdateRunnable(IUpdatable... updateable) {
    addUpdatables(updateable);
  }
  
  public void addUpdatables(IUpdatable... updateable) {
    allUpdateable.addAll(Arrays.asList(updateable));
  }

  @Override
  public void run() {
    for (IUpdatable updateable : this.allUpdateable) {
      if (updateable != null) {
        updateable.update();
      }
    }
  }
}