package net.sf.anathema.character.trait.display;

import static org.junit.Assert.*;
import net.sf.anathema.character.experience.Experience;
import net.sf.anathema.character.experience.IExperience;
import net.sf.anathema.character.trait.BasicTrait;
import net.sf.anathema.character.trait.DisplayTrait;
import net.sf.anathema.character.trait.DummyTraitTemplate;
import net.sf.anathema.character.trait.IBasicTrait;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.Identificate;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class DisplayTraitToggleExperienceTest {

  private static final int EXPERIENCE_VALUE = 4;
  private static final int CREATION_VALUE = 2;
  private IExperience experience;
  private DisplayTrait displayTrait;

  @Before
  public void createTrait() throws Exception {
    IBasicTrait basicTrait = new BasicTrait(new Identificate("Wies‰‰‰")); //$NON-NLS-1$
    basicTrait.getCreationModel().setValue(CREATION_VALUE);
    basicTrait.getExperiencedModel().setValue(EXPERIENCE_VALUE);
    experience = new Experience();
    this.displayTrait = new DisplayTrait(basicTrait, experience, new DummyTraitTemplate());
  }
  
  @Test
  public void experienceChangeTriggersValueChangeEvent() throws Exception {
    IChangeListener changeListener = EasyMock.createMock(IChangeListener.class);
    changeListener.changeOccured();
    displayTrait.addValueChangeListener(changeListener);
    EasyMock.replay(changeListener);
    experience.setExperienced(true);
    EasyMock.verify(changeListener);
    assertEquals(EXPERIENCE_VALUE, displayTrait.getValue());
  }
}