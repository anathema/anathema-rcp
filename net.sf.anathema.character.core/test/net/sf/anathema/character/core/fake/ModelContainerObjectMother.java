package net.sf.anathema.character.core.fake;

import static org.easymock.EasyMock.*;
import net.sf.anathema.character.core.character.IModel;
import net.sf.anathema.character.core.character.IModelContainer;

public class ModelContainerObjectMother {

  public static IModelContainer CreateForModelId(String modelId, IModel model) {
    IModelContainer container = createMock(IModelContainer.class);
    expect(container.getModel(modelId)).andStubReturn(model);
    replay(container);
    return container;
  }
}