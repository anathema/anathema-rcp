package net.sf.anathema.character.attributes.model;

import static org.easymock.EasyMock.*;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollectionFactory;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.interactive.ModelContainerObjectMother;

import org.easymock.EasyMock;

public class AttributeContextObjectMother {

  public static ITraitCollectionContext createContext(TraitGroup... traitGroups) {
    ITraitCollectionModel attributes = TraitCollectionFactory.create(traitGroups, new DummyTemplateFactory());
    DummyExperience experience = new DummyExperience();
    IModelContainer container = ModelContainerObjectMother.create(experience);
    ITraitCollectionContext context = EasyMock.createNiceMock(ITraitCollectionContext.class);
    EasyMock.expect(context.getTraitGroups()).andReturn(traitGroups).anyTimes();
    EasyMock.expect(context.getCollection()).andReturn(attributes).anyTimes();
    EasyMock.expect(context.getModelContainer()).andReturn(container).anyTimes();
    EasyMock.expect(context.getMinimumValue(isA(String.class))).andReturn(0).anyTimes();
    EasyMock.replay(context);
    return context;
  }
}