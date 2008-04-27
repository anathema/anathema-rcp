package net.sf.anathema.character.trait.interactive;

import static org.junit.Assert.*;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.fake.DummyTraitPreferences;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class InteractiveTraitWithLeaveUnchangedTest {

  private DummyExperience experience;
  private BasicTrait basicTrait;
  private InteractiveTrait interactiveTrait;

  @Before
  public final void createTraitWithExperienceValue3AndCreationValue2() {
    experience = new DummyExperience();
    basicTrait = new BasicTrait(new Identificate("test")); //$NON-NLS-1$
    basicTrait.getCreationModel().setValue(2);
    basicTrait.getExperiencedModel().setValue(3);
    interactiveTrait = new InteractiveTrait(
        basicTrait,
        ModelContainerObjectMother.create(experience),
        EasyMock.createMock(IInteractiveFavorization.class),
        new DummyTraitTemplate(),
        new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged));
  }

  @Test
  public void whenCreationValueIsSetTo4ExperienceValueRemainsUnchanged() throws Exception {
    experience.setExperienced(false);
    interactiveTrait.setValue(4);
    assertEquals(3, basicTrait.getExperiencedModel().getValue());
  }

  @Test
  public void experienceChangeTriggersValueChangeEvent() throws Exception {
    IChangeListener changeListener = EasyMock.createMock(IChangeListener.class);
    changeListener.stateChanged();
    interactiveTrait.addChangeListener(changeListener);
    EasyMock.replay(changeListener);
    experience.setExperienced(true);
    EasyMock.verify(changeListener);
  }
}