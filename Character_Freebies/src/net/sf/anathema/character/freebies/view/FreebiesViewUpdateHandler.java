package net.sf.anathema.character.freebies.view;

import net.sf.anathema.basics.eclipse.ui.IPartContainer;
import net.sf.anathema.character.core.model.CurrentModelUpdatable;
import net.sf.anathema.character.core.model.IModelProvider;
import net.sf.anathema.character.core.model.ModelCache;
import net.sf.anathema.lib.ui.IUpdatable;
import net.sf.anathema.view.valuelist.IViewUpdateHandler;
import net.sf.anathema.view.valuelist.EditorDependentUpdateHandler;


public class FreebiesViewUpdateHandler implements IViewUpdateHandler {

  private final IModelProvider modelProvider;
  private final EditorDependentUpdateHandler updateHandler = new EditorDependentUpdateHandler();

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
    updateHandler.addUpdatable(updateable);
    updateHandler.addUpdatable(new CurrentModelUpdatable(updateable, partContainer, modelProvider));
    updateHandler.init(partContainer);
  }
  
  @Override
  public void dispose() {
    updateHandler.dispose();
  }
}