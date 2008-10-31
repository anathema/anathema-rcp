package net.sf.anathema.character.points.view;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.CurrentModelUpdatable;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.lib.ui.AggregatedDisposable;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.view.valuelist.EditorDependentUpdateHandler;
import net.sf.anathema.view.valuelist.IViewUpdateHandler;

public class PointViewUpdateHandler implements IViewUpdateHandler {

  private AggregatedDisposable disposables = new AggregatedDisposable();
  private CharacterPointsUpdatable experienceUpdateable;
  private final EditorDependentUpdateHandler updateHandler = new EditorDependentUpdateHandler();
  private final IModelCollection modelProvider;
  
  public PointViewUpdateHandler() {
    this(ModelCache.getInstance());
  }

  public PointViewUpdateHandler(IModelCollection modelProvider) {
    this.modelProvider = modelProvider;
  }

  public String getTitle() {
    return new CharacterPointsViewTitleFactory(experienceUpdateable).create();
  }

  public void init(IPartContainer partContainer, IUpdatable updateable) {
    experienceUpdateable = new CharacterPointsUpdatable(partContainer, updateable, modelProvider);
    updateHandler.addUpdatable(experienceUpdateable);
    updateHandler.addUpdatable(updateable);
    updateHandler.addUpdatable(new CurrentModelUpdatable(updateable, partContainer, modelProvider));
    updateHandler.init(partContainer);
  }

  @Override
  public void dispose() {
    disposables.dispose();
  }
}