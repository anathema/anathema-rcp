package net.sf.anathema.character.trait.interactive;

import static org.easymock.EasyMock.*;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.IExperience;

public class ModelContainerObjectMother {

  public static IModelContainer create(IExperience experience) {
    IModelContainer container = createMock(IModelContainer.class);
    expect(container.getModel(IExperience.MODEL_ID)).andReturn(experience).anyTimes();
    replay(container);
    return container;
  }
}