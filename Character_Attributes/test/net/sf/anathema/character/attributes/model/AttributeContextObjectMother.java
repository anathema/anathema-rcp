package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.TraitTemplate;

import org.easymock.EasyMock;

public class AttributeContextObjectMother {

  public static ITraitCollectionContext createContext(TraitGroup[] traitGroups) {
    ITraitCollectionModel attributes = Attributes.create(traitGroups, new TraitTemplate());
    DummyExperience experience = new DummyExperience();
    ITraitCollectionContext context = EasyMock.createNiceMock(ITraitCollectionContext.class);
    EasyMock.expect(context.getTraitGroups()).andReturn(traitGroups).anyTimes();
    EasyMock.expect(context.getCollection()).andReturn(attributes).anyTimes();
    EasyMock.expect(context.getExperience()).andReturn(experience).anyTimes();
    EasyMock.replay(context);
    return context;
  }
}