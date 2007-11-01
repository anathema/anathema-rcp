package net.sf.anathema.character.trait.interactive;

import static org.junit.Assert.*;
import net.disy.commons.core.model.listener.IChangeListener;
import net.sf.anathema.character.experience.DummyExperience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.InteractiveTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.character.trait.preference.ExperienceTraitTreatment;
import net.sf.anathema.character.trait.preference.ITraitPreferences;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class DisplayTraitToggleExperienceTest {

  private static final int EXPERIENCE_VALUE = 4;
  private static final int CREATION_VALUE = 2;
  private IExperience experience;
  private InteractiveTrait displayTrait;

  @Before
  public void createTrait() throws Exception {
    IBasicTrait basicTrait = new BasicTrait(new Identificate("Wies‰‰‰")); //$NON-NLS-1$
    basicTrait.getCreationModel().setValue(CREATION_VALUE);
    basicTrait.getExperiencedModel().setValue(EXPERIENCE_VALUE);
    this.experience = new DummyExperience();
    ITraitPreferences traitPreferences = new DummyTraitPreferences(ExperienceTraitTreatment.LeaveUnchanged);
    this.displayTrait = new InteractiveTrait(basicTrait, experience, null, new DummyTraitTemplate(), traitPreferences);
  }

  @Test
  public void experienceChangeTriggersValueChangeEvent() throws Exception {
    IChangeListener changeListener = EasyMock.createMock(IChangeListener.class);
    changeListener.stateChanged();
    displayTrait.addChangeListener(changeListener);
    EasyMock.replay(changeListener);
    experience.setExperienced(true);
    EasyMock.verify(changeListener);
    assertEquals(EXPERIENCE_VALUE, displayTrait.getValue());
  }
}