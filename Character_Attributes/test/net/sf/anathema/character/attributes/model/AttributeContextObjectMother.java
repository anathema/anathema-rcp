package net.sf.anathema.character.attributes.model;

import static org.easymock.EasyMock.*;
import net.sf.anathema.character.core.character.IModelContainer;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.collection.ITraitCollectionModel;
import net.sf.anathema.character.trait.collection.TraitCollectionFactory;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.interactive.ModelContainerObjectMother;
import net.sf.anathema.character.trait.template.EssenceSensitiveTraitTemplate;
import net.sf.anathema.character.trait.template.ITraitTemplate;

import org.easymock.EasyMock;

public class AttributeContextObjectMother {

  public static ITraitCollectionContext createContext(TraitGroup... traitGroups) {
    ITraitCollectionModel attributes = TraitCollectionFactory.create(traitGroups, new DummyTemplateFactory(
        new EssenceSensitiveTraitTemplate()));
    DummyExperience experience = new DummyExperience();
    ITraitTemplate template = new DummyTraitTemplate();
    IModelContainer container = ModelContainerObjectMother.create(experience);
    ITraitCollectionContext context = EasyMock.createNiceMock(ITraitCollectionContext.class);
    EasyMock.expect(context.getTraitGroups()).andReturn(traitGroups).anyTimes();
    EasyMock.expect(context.getCollection()).andReturn(attributes).anyTimes();
    EasyMock.expect(context.getModelContainer()).andReturn(container).anyTimes();
    EasyMock.expect(context.getTraitTemplate(isA(String.class))).andReturn(template).anyTimes();
    EasyMock.replay(context);
    return context;
  }
}