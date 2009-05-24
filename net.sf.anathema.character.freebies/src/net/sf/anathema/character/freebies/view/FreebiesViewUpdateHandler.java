package net.sf.anathema.character.freebies.view;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.character.IModelCollection;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.character.core.model.ModelChangeDisposable;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.view.valuelist.EditorDependentUpdateHandler;
import net.sf.anathema.view.valuelist.IViewUpdateHandler;

public class FreebiesViewUpdateHandler implements IViewUpdateHandler {

  private final IModelCollection modelProvider;
  private final EditorDependentUpdateHandler updateHandler = new EditorDependentUpdateHandler();

  public FreebiesViewUpdateHandler() {
    this(ModelCache.getInstance());
  }

  public FreebiesViewUpdateHandler(IModelCollection modelProvider) {
    this.modelProvider = modelProvider;
  }

  @Override
  public String getTitle() {
    return null;
  }

  @Override
  public void init(IPartContainer partContainer, IUpdatable updateable) {
    updateHandler.addUpdatable(updateable);
    updateHandler.addDisposable(new ModelChangeDisposable(updateable, modelProvider));
    updateHandler.init(partContainer);
  }

  @Override
  public void dispose() {
    updateHandler.dispose();
  }
}