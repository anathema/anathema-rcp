package net.sf.anathema.character.freebies.view;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.basics.eclipse.ui.PartListening;
import net.sf.anathema.basics.eclipse.ui.TopPartListener;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.view.valuelist.IUpdatable;
import net.sf.anathema.view.valuelist.IViewUpdateHandler;
import net.sf.anathema.view.valuelist.UpdateRunnable;


public class FreebiesViewUpdateHandler implements IViewUpdateHandler {

  private AggregatedDisposable disposables = new AggregatedDisposable();
  private final IModelProvider modelProvider;

  public FreebiesViewUpdateHandler() {
    this(ModelCache.getInstance());
  }
  
  public FreebiesViewUpdateHandler(IModelProvider modelProvider) {
    this.modelProvider = modelProvider;
  }
  
  @Override
  public String getTitle() {
    return null;
  }

  @Override
  public void init(IPartContainer partContainer, IUpdatable updateable) {
    CurrentModelUpdatable modelUpdatable = new CurrentModelUpdatable(updateable, partContainer, modelProvider);
    UpdateRunnable runnable = new UpdateRunnable(updateable, modelUpdatable);
    runnable.run();
    TopPartListener topPartListener = new TopPartListener(runnable);
    disposables.addDisposable(modelUpdatable);
    disposables.addDisposable(new PartListening(topPartListener, partContainer));
  }
  
  @Override
  public void dispose() {
    disposables.dispose();
  }
}