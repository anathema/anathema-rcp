package net.sf.anathema.character.points;

import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IDisposable;

import org.eclipse.ui.IWorkbenchPartSite;

public class PointViewUpdateHandler implements IDisposable{

  private AggregatedDisposable disposables = new AggregatedDisposable();
  
  public String getTitle() {
    return "Hasäntum";
  }

  public void init(IWorkbenchPartSite site, IUpdateable updateable) {
    TopPartListener topPartListener = new TopPartListener(new UpdateRunnable(updateable));
    disposables.addDisposable(new PartListening(topPartListener, site.getWorkbenchWindow().getPartService()));
  }

  @Override
  public void dispose() {
    disposables.dispose();
    
  }
}