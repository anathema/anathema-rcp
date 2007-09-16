package net.sf.anathema.character.attributes.points;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.attributes.TraitCollectionObjectMother;
import net.sf.anathema.character.attributes.points.coverage.AttributeGroupPriorityCalculator;
import net.sf.anathema.character.trait.collection.ITraitCollectionContext;
import net.sf.anathema.character.trait.group.ITraitGroup;
import net.sf.anathema.character.trait.group.TraitGroup;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AttributeGroupPriorityCalculatorTest {

  private AttributeGroupPriorityCalculator calculator;
  private ITraitCollectionContext context;

  @Before
  public void createCalculator() {
    this.context = EasyMock.createNiceMock(ITraitCollectionContext.class);
    this.calculator = new AttributeGroupPriorityCalculator(context);
  }

  @Test
  public void singleGroupHasPrimaryPriority() throws Exception {
    TraitGroup traitGroup = new TraitGroup("Single"); //$NON-NLS-1$
    EasyMock.expect(context.getTraitGroups()).andReturn(new ITraitGroup[] { traitGroup });
    EasyMock.replay(context);
    assertEquals(AttributePointCalculator.PRIMARY, calculator.getPriority(traitGroup));
  }

  @Test
  public void identicalGroupsAreNotReordered() throws Exception {
    Identificate firstId = new Identificate("Trait"); //$NON-NLS-1$
    Identificate secondId = new Identificate("otherTrait"); //$NON-NLS-1$
    TraitGroup firstGroup = new TraitGroup("First", firstId.getId()); //$NON-NLS-1$
    TraitGroup secondGroup = new TraitGroup("Second", secondId.getId()); //$NON-NLS-1$
    TraitGroup thirdGroup = new TraitGroup("Third"); //$NON-NLS-1$
    DummyTraitCollection collection = TraitCollectionObjectMother.createTraitCollection(firstId, secondId);
    EasyMock.expect(context.getCollection()).andReturn(collection).anyTimes();
    EasyMock.expect(context.getTraitGroups())
        .andReturn(new ITraitGroup[] { firstGroup, secondGroup, thirdGroup })
        .anyTimes();
    EasyMock.replay(context);
    assertEquals(AttributePointCalculator.PRIMARY, calculator.getPriority(firstGroup));
    assertEquals(AttributePointCalculator.SECONDARY, calculator.getPriority(secondGroup));
    assertEquals(AttributePointCalculator.TERTIARY, calculator.getPriority(thirdGroup));
  }

  @Test
  public void highestSpendingGetsHighestPriority() throws Exception {
    Identificate firstId = new Identificate("Trait"); //$NON-NLS-1$
    Identificate secondId = new Identificate("otherTrait"); //$NON-NLS-1$
    TraitGroup firstGroup = new TraitGroup("First", firstId.getId()); //$NON-NLS-1$
    TraitGroup secondGroup = new TraitGroup("Second", secondId.getId()); //$NON-NLS-1$
    DummyTraitCollection collection = TraitCollectionObjectMother.createTraitCollection(firstId, secondId);
    collection.getTrait(secondId.getId()).getCreationModel().setValue(3);
    EasyMock.expect(context.getCollection()).andReturn(collection).anyTimes();
    EasyMock.expect(context.getTraitGroups()).andReturn(new ITraitGroup[] { firstGroup, secondGroup }).anyTimes();
    EasyMock.replay(context);
    assertEquals(AttributePointCalculator.PRIMARY, calculator.getPriority(secondGroup));
  }
}