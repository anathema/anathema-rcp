package net.sf.anathema.character.points;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;


public class PointViewUpdateHandler implements IDisposable {

  private AggregatedDisposable disposables = new AggregatedDisposable();
  private ExperienceUpdateable experienceUpdateable;

  public String getTitle() {
    return new ExperienceViewTitleFactory(experienceUpdateable).create();
  }

  public void init(IPartContainer partContainer, IUpdateable updateable) {
    experienceUpdateable = new ExperienceUpdateable(partContainer);
    UpdateRunnable runnable = new UpdateRunnable(experienceUpdateable, updateable);
    TopPartListener topPartListener = new TopPartListener(runnable);
    disposables.addDisposable(new PartListening(topPartListener, partContainer));
  }

  @Override
  public void dispose() {
    disposables.dispose();
  }
}