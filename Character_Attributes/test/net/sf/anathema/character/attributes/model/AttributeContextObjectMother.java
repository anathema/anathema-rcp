package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.character.trait.rules.TraitTemplate;

import org.easymock.EasyMock;

public class AttributeContextObjectMother {

  public static IAttributeCharacterContext createContext(TraitGroup[] traitGroups) {
    IAttributes attributes = Attributes.create(traitGroups, new TraitTemplate());
    DummyExperience experience = new DummyExperience();
    IAttributeCharacterContext context = EasyMock.createNiceMock(IAttributeCharacterContext.class);
    EasyMock.expect(context.getTraitGroups()).andReturn(traitGroups).anyTimes();
    EasyMock.expect(context.getAttributes()).andReturn(attributes).anyTimes();
    EasyMock.expect(context.getExperience()).andReturn(experience).anyTimes();
    EasyMock.replay(context);
    return context;
  }
}