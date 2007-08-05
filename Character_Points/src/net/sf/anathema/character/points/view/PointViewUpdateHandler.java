package net.sf.anathema.character.points.view;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.view.valuelist.IUpdatable;
import net.sf.anathema.view.valuelist.IViewUpdateHandler;
import net.sf.anathema.view.valuelist.UpdateRunnable;

public class PointViewUpdateHandler implements IViewUpdateHandler {

  private AggregatedDisposable disposables = new AggregatedDisposable();
  private CharacterPointsUpdatable experienceUpdateable;

  public String getTitle() {
    return new CharacterPointsViewTitleFactory(experienceUpdateable).create();
  }

  public void init(IPartContainer partContainer, IUpdatable updateable) {
    experienceUpdateable = new CharacterPointsUpdatable(partContainer, updateable);
    UpdateRunnable runnable = new UpdateRunnable(experienceUpdateable, updateable);
    TopPartListener topPartListener = new TopPartListener(runnable);
    disposables.addDisposable(new PartListening(topPartListener, partContainer));
  }

  @Override
  public void dispose() {
    disposables.dispose();
  }
}